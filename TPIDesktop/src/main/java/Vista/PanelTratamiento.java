/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author natal
 */

import javax.swing.*;
import java.awt.*;

/*
 Panel donde el veterinario carga los tratamientos del gato.
 */
public class PanelTratamiento extends JPanel {

        private JTextField txtCodigoTratamiento;
    private JTextField txtNombreTratamiento;
    private JTextField txtMedicamento;
    private JButton btnGuardar;

    public PanelTratamiento() {
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Código Tratamiento:"));
        txtCodigoTratamiento = new JTextField();
        add(txtCodigoTratamiento);

        add(new JLabel("Nombre del tratamiento:"));
        txtNombreTratamiento = new JTextField();
        add(txtNombreTratamiento);

        add(new JLabel("Medicamento:"));
        txtMedicamento = new JTextField();
        add(txtMedicamento);

        btnGuardar = new JButton("Guardar Tratamiento");
        add(new JLabel());
        add(btnGuardar);
    }
}
