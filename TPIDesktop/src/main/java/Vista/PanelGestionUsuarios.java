package Vista;

import Controlador.ControladorUsuarios;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * panel donde el administrador puede crear, ver, modificar y eliminar usuarios
 */
public class PanelGestionUsuarios extends JPanel {

    private final JTextField txtUsername;
    private final JTextField txtContrasenia;
    private final JTextField txtTelefono;
    private final JComboBox<String> cbRol;

    private final JButton btnCrear;
    private final JButton btnModificar;
    private final JButton btnEliminar;

    private final JTable tablaUsuarios;
    private final DefaultTableModel modeloTabla;

    private final ControladorUsuarios controlador;

    public PanelGestionUsuarios() {
        controlador = new ControladorUsuarios();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gestión de Usuarios"));

        // panel superior formulario
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));

        panelForm.add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        panelForm.add(txtUsername);

        panelForm.add(new JLabel("Contraseña:"));
        txtContrasenia = new JTextField();
        panelForm.add(txtContrasenia);

        panelForm.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelForm.add(txtTelefono);

        panelForm.add(new JLabel("Rol:"));
        cbRol = new JComboBox<>(new String[]{"administrador", "veterinario", "voluntario", "familia"});
        panelForm.add(cbRol);

        btnCrear = new JButton("Crear Usuario");
        panelForm.add(new JLabel());
        panelForm.add(btnCrear);

        add(panelForm, BorderLayout.NORTH);

        // panel central tabla 
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Usuario", "Rol", "Teléfono"}, 0);
        tablaUsuarios = new JTable(modeloTabla);
        add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);

        // panel inferior botones 
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // eventos 
        btnCrear.addActionListener(e -> crearUsuario());
        btnModificar.addActionListener(e -> modificarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());

        // carga inicial 
        refrescarTabla();
    }

    private void crearUsuario() {
        try {
            String username = txtUsername.getText().trim();
            String pass = txtContrasenia.getText().trim();
            String rol = (String) cbRol.getSelectedItem();
            String telefonoTexto = txtTelefono.getText().trim();

            if (username.isEmpty() || pass.isEmpty() || telefonoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completá todos los campos.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int telefono = Integer.parseInt(telefonoTexto);

            Usuario nuevo = new Usuario();
            nuevo.setUsername(username);
            nuevo.setContrasenia(pass);
            nuevo.setRol(rol);
            nuevo.setTelefono(telefono);

            controlador.crearUsuario(nuevo);

            JOptionPane.showMessageDialog(this, "Usuario creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            refrescarTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El teléfono debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccioná un usuario para modificar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nuevoUser = JOptionPane.showInputDialog(this, "Nuevo nombre de usuario:");
        String nuevoPass = JOptionPane.showInputDialog(this, "Nueva contraseña:");
        String nuevoTel = JOptionPane.showInputDialog(this, "Nuevo teléfono:");

        try {
            Usuario u = controlador.listarUsuarios().stream()
                    .filter(us -> us.getIdUsuario() == id)
                    .findFirst()
                    .orElse(null);

            if (u != null) {
                if (nuevoUser != null && !nuevoUser.isEmpty()) u.setUsername(nuevoUser);
                if (nuevoPass != null && !nuevoPass.isEmpty()) u.setContrasenia(nuevoPass);
                if (nuevoTel != null && !nuevoTel.isEmpty()) u.setTelefono(Integer.parseInt(nuevoTel));

                controlador.modificarUsuario(u);
                JOptionPane.showMessageDialog(this, "Usuario modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                refrescarTabla();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccioná un usuario para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que querés eliminar el usuario seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controlador.eliminarUsuario(id);
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                refrescarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refrescarTabla() {
        modeloTabla.setRowCount(0);
        List<Usuario> lista = controlador.listarUsuarios();
        for (Usuario u : lista) {
            modeloTabla.addRow(new Object[]{u.getIdUsuario(), u.getUsername(), u.getRol(), u.getTelefono()});
        }
    }

    private void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
        txtTelefono.setText("");
    }
}

