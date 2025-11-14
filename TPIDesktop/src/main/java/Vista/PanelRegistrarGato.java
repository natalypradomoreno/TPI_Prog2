package Vista;

import modelo.Gato;
import modelo.Zona;
import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelRegistrarGato extends JPanel {

    private JTextField txtCodigoQR;
    private JTextField txtNombre;
    private JTextField txtRaza;
    private JTextField txtColor;
    private JTextField txtCaracteristicas;
    private JComboBox<String> cbEstado;
    private JComboBox<Zona> cbZona;
    private JButton btnGuardar;

    public PanelRegistrarGato() {
        setLayout(new GridLayout(8, 2, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(new JLabel("Código QR:"));
        txtCodigoQR = new JTextField();
        add(txtCodigoQR);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Raza:"));
        txtRaza = new JTextField();
        add(txtRaza);

        add(new JLabel("Color:"));
        txtColor = new JTextField();
        add(txtColor);

        add(new JLabel("Características:"));
        txtCaracteristicas = new JTextField();
        add(txtCaracteristicas);

        add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"Sano", "En tratamiento", "Esterilizado", "Enfermo"});
        add(cbEstado);

        add(new JLabel("Zona:"));
        cbZona = new JComboBox<>();
        cargarZonas();
        add(cbZona);

        btnGuardar = new JButton("Guardar Gato");
        add(new JLabel());
        add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarGato());
    }

    private void cargarZonas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
        EntityManager em = emf.createEntityManager();
        try {
            List<Zona> zonas = em.createQuery("SELECT z FROM Zona z", Zona.class).getResultList();
            for (Zona z : zonas) {
                cbZona.addItem(z);
            }
        } finally {
            em.close();
            emf.close();
        }
    }

    private void guardarGato() {
        if (txtCodigoQR.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completá código QR y nombre.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Gato gato = new Gato();
            gato.setCodigoQR(txtCodigoQR.getText().trim());
            gato.setNombre(txtNombre.getText().trim());
            gato.setRaza(txtRaza.getText().trim());
            gato.setColor(txtColor.getText().trim());
            gato.setCaracteristicas(txtCaracteristicas.getText().trim());
            gato.setEstado((String) cbEstado.getSelectedItem());
            gato.setSituacion(0);           // 0 = en adopcion
            gato.setCertificadoAptitud(false); // siempre false al crear

            Zona zSel = (Zona) cbZona.getSelectedItem();
            if (zSel != null) {
                // traemos la zona gestionada
                Zona zonaRef = em.find(Zona.class, zSel.getIdZona());
                gato.setZona(zonaRef);
            }

            em.persist(gato);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(this, "Gato guardado.", "OK", JOptionPane.INFORMATION_MESSAGE);

            txtCodigoQR.setText("");
            txtNombre.setText("");
            txtRaza.setText("");
            txtColor.setText("");
            txtCaracteristicas.setText("");
            cbEstado.setSelectedIndex(0);
            if (cbZona.getItemCount() > 0) cbZona.setSelectedIndex(0);

        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            em.close();
            emf.close();
        }
    }
}
