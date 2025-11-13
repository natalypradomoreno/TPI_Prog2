/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author natal
 */
import Controlador.ControladorHogares;
import Controlador.ControladorFamilias;
import modelo.FamiliaUsuario;
import modelo.Hogar;

import javax.swing.*;
import java.awt.*;

public class PanelSeleccionHogar extends JPanel {

    private final FamiliaUsuario familia;
    private final ControladorHogares controladorHogar;
    private final ControladorFamilias controladorFamilia;

    public PanelSeleccionHogar(FamiliaUsuario fam) {

        this.familia = fam;
        this.controladorHogar = new ControladorHogares();
        this.controladorFamilia = new ControladorFamilias();

        setLayout(new GridLayout(12, 1, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Registro de Hogar"));

        // ====================================================
        // SECCIÓN A: CREAR NUEVO HOGAR
        // ====================================================
        add(new JLabel("CREAR NUEVO HOGAR"));
        add(new JLabel("Dirección:"));
        JTextField txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("Tipo de hogar:"));
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{
                "1 - Tránsito",
                "2 - Adoptivo"
        });
        add(cbTipo);

        JButton btnCrear = new JButton("Crear hogar y vincular");
        add(btnCrear);

        add(new JLabel("---------------------------------------"));

        // ====================================================
        // SECCIÓN B: VINCULAR HOGAR EXISTENTE
        // ====================================================
        add(new JLabel("YA TENGO UN HOGAR REGISTRADO"));

        add(new JLabel("ID Hogar:"));
        JTextField txtIdHogar = new JTextField();
        add(txtIdHogar);

        JButton btnVincular = new JButton("Vincular mi hogar");
        add(btnVincular);

        // ====================================================
        // EVENTO → CREAR HOGAR
        // ====================================================
        btnCrear.addActionListener(e -> {

            String direccion = txtDireccion.getText().trim();
            String tipo = cbTipo.getSelectedItem().toString().substring(0, 1); // “1” o “2”

            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingresá una dirección.",
                        "Faltan datos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Hogar h = controladorHogar.crearHogarVinculado(direccion, tipo, familia);

            if (h != null) {
                JOptionPane.showMessageDialog(this,
                        "Hogar creado y vinculado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // abrir ventana familia actualizada
                SwingUtilities.getWindowAncestor(this).dispose();
                new VentanaFamilia(familia).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al crear o vincular hogar.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // ====================================================
        // EVENTO → VINCULAR HOGAR EXISTENTE
        // ====================================================
        btnVincular.addActionListener(e -> {

            String idTxt = txtIdHogar.getText().trim();

            if (idTxt.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingresá un ID de hogar.",
                        "Faltan datos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id;

            try {
                id = Integer.parseInt(idTxt);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "El ID debe ser un número.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar hogar
            Hogar h = controladorHogar.buscarPorId(id);

            if (h == null) {
                JOptionPane.showMessageDialog(this,
                        "No existe un hogar con ese ID.\nDebe crear uno nuevo.",
                        "Hogar no encontrado",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vincular hogar a la familia
            try {
                familia.setHogar(h);
                controladorFamilia.editarFamilia(familia);

                JOptionPane.showMessageDialog(this,
                        "Hogar vinculado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Abrir ventana de familia
                SwingUtilities.getWindowAncestor(this).dispose();
                new VentanaFamilia(familia).setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al vincular hogar.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
