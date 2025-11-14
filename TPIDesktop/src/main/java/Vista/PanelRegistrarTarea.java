package Vista;

import Controlador.ControladorTareas;
import Controlador.ControladorGato;
import Controlador.ControladorZonas;
import modelo.Gato;
import modelo.Zona;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 panel donde el voluntario registra una nueva tarea
 permite elegir tipo, fecha, hora, descripcion, zona,
 y asociar un gato y un voluntario existentes
 */
public class PanelRegistrarTarea extends JPanel {

    private JComboBox<String> cbTipoTarea;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextArea txtDescripcion;
    private JComboBox<String> cbZona;
    private JTextField txtCodigoQR;
    private JTextField txtIdVoluntario;
    private JButton btnBuscarGato;
    private JButton btnBuscarVoluntario;
    private JButton btnGuardar;

    private final ControladorTareas controlador;
    private final ControladorGato controladorGato;
    public PanelRegistrarTarea() {
        controlador = new ControladorTareas();
        controladorGato = new ControladorGato();

        setLayout(new BorderLayout(10, 10));

        JPanel panelArriba = new JPanel(new GridLayout(8, 2, 5, 5));

        panelArriba.add(new JLabel("Tipo de tarea:"));
        cbTipoTarea = new JComboBox<>(new String[]{
            "Alimentación", "Captura para castración", "Control veterinario", "Transporte a hogar"
        });
        panelArriba.add(cbTipoTarea);

        panelArriba.add(new JLabel("Fecha (yyyy-MM-dd):"));
        txtFecha = new JTextField();
        panelArriba.add(txtFecha);

        panelArriba.add(new JLabel("Hora (HH:mm:ss):"));
        txtHora = new JTextField();
        panelArriba.add(txtHora);

        panelArriba.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextArea(2, 15);
        panelArriba.add(txtDescripcion);

        // campo codigo QR con boton buscar
        panelArriba.add(new JLabel("Código QR del gato:"));
        JPanel panelGato = new JPanel(new BorderLayout());
        txtCodigoQR = new JTextField();
        btnBuscarGato = new JButton("Buscar");
        panelGato.add(txtCodigoQR, BorderLayout.CENTER);
        panelGato.add(btnBuscarGato, BorderLayout.EAST);
        panelArriba.add(panelGato);

        // campo ID voluntario con boton buscar
        panelArriba.add(new JLabel("ID Voluntario:"));
        JPanel panelVol = new JPanel(new BorderLayout());
        txtIdVoluntario = new JTextField();
        btnBuscarVoluntario = new JButton("Buscar");
        panelVol.add(txtIdVoluntario, BorderLayout.CENTER);
        panelVol.add(btnBuscarVoluntario, BorderLayout.EAST);
        panelArriba.add(panelVol);

        // combo zonas
        panelArriba.add(new JLabel("Zona:"));
        cbZona = new JComboBox<>();
        panelArriba.add(cbZona);

        add(panelArriba, BorderLayout.NORTH);

        btnGuardar = new JButton("Registrar Tarea");
        add(btnGuardar, BorderLayout.SOUTH);
        btnBuscarGato.addActionListener(e -> buscarGato());
        btnBuscarVoluntario.addActionListener(e -> buscarVoluntario());
        btnGuardar.addActionListener(e -> guardarTarea());

        cargarZonas();
    }

    private void cargarZonas() {
        cbZona.removeAllItems();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
        EntityManager em = emf.createEntityManager();

        try {
            List<Zona> zonas = em.createQuery("SELECT z FROM Zona z", Zona.class).getResultList();
            for (Zona z : zonas) {
                cbZona.addItem(z.getNombreZona());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar zonas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            em.close();
            emf.close();
        }
    }

    private void buscarGato() {
        String codigo = txtCodigoQR.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresá un código QR.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Gato gato = controladorGato.buscarGatoPorCodigo(codigo);
        if (gato != null) {
            JOptionPane.showMessageDialog(this,
                    "Gato encontrado:\nNombre: " + gato.getNombre() + "\nEstado: " + gato.getEstado(),
                    "Confirmado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se encontró ningún gato con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarVoluntario() {
        String idTexto = txtIdVoluntario.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresá un ID de voluntario.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            boolean existe = controlador.verificarVoluntario(id);
            if (existe) {
                JOptionPane.showMessageDialog(this, "Voluntario verificado correctamente.", "OK", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No existe un voluntario con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarTarea() {
        try {
            String tipo = (String) cbTipoTarea.getSelectedItem();
            String fechaTexto = txtFecha.getText().trim();
            String horaTexto = txtHora.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String codigoQR = txtCodigoQR.getText().trim();
            int idVoluntario = Integer.parseInt(txtIdVoluntario.getText().trim());
            String zona = (String) cbZona.getSelectedItem();

            if (fechaTexto.isEmpty() || horaTexto.isEmpty() || descripcion.isEmpty() || codigoQR.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completá todos los campos obligatorios.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Date fechaSQL = Date.valueOf(fechaTexto); // formato yyyy-MM-dd
            Time horaSQL = Time.valueOf(horaTexto);   // formato HH:mm:ss

            boolean ok = controlador.guardarTarea(tipo, descripcion, fechaSQL, horaSQL, codigoQR, idVoluntario, zona);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Tarea registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtDescripcion.setText("");
                txtCodigoQR.setText("");
                txtIdVoluntario.setText("");
                txtFecha.setText("");
                txtHora.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la tarea.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Formato incorrecto. Fecha: yyyy-MM-dd | Hora: HH:mm:ss", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
