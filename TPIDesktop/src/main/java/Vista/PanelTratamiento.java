package Vista;


import Controlador.ControladorTratamientos;
import javax.swing.*;
import java.awt.*;

/*
 panel donde el veterinario carga los tratamientos del gato
 */
public class PanelTratamiento extends JPanel {


    private final JTextField txtNombreTratamiento;
    private final JTextField txtMedicamento;
    private final JButton btnGuardar;
    private final ControladorTratamientos controlador;
    public PanelTratamiento() {
        setLayout(new GridLayout(4, 2, 5, 5));
         this.controlador = new ControladorTratamientos();

        add(new JLabel("Nombre del tratamiento:"));
        txtNombreTratamiento = new JTextField();
        add(txtNombreTratamiento);

        add(new JLabel("Medicamento:"));
        txtMedicamento = new JTextField();
        add(txtMedicamento);

        btnGuardar = new JButton("Guardar Tratamiento");
        add(new JLabel());
        add(btnGuardar);
        btnGuardar.addActionListener(e -> guardarTratamiento());
    }
    private void guardarTratamiento() {
    String nombre = txtNombreTratamiento.getText().trim();
    String medicamento = txtMedicamento.getText().trim();

    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Completá el nombre del tratamiento.",
                "Faltan datos",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    boolean ok = controlador.guardarTratamiento(nombre, medicamento);

    if (ok) {
        JOptionPane.showMessageDialog(this,
                "Tratamiento guardado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        txtNombreTratamiento.setText("");
        txtMedicamento.setText("");

    } else {
        JOptionPane.showMessageDialog(this,
                "Ya existe un tratamiento con ese nombre.",
                "Duplicado",
                JOptionPane.ERROR_MESSAGE);
    }
}


}
