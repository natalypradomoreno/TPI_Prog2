/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author natal
 */

import Controlador.ControladorUsuarios;
import modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 Vista que representa la pantalla de inicio de sesión.
 Forma parte de la capa de Vista dentro del patrón MVC.
 Se comunica con el ControladorUsuarios para validar los datos ingresados.
 */
public class VentanaLogin extends JFrame {

    // Componentes de la interfaz
    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JButton btnIngresar;

    // Referencia al controlador
    private ControladorUsuarios controlador;

    public VentanaLogin() {
        // Configuración general de la ventana
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 180);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Instanciamos el controlador (parte del MVC)
        controlador = new ControladorUsuarios();

        // Armado del panel con los campos
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();

        JLabel lblContrasenia = new JLabel("Contraseña:");
        txtContrasenia = new JPasswordField();

        btnIngresar = new JButton("Iniciar sesión");

        // Armamos el panel
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasenia);
        panel.add(txtContrasenia);
        panel.add(new JLabel());
        panel.add(btnIngresar);

        add(panel, BorderLayout.CENTER);

        // Acción del botón → se delega al método del Controlador
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarLogin();
            }
        });
    }

    /*
     Método que toma los datos de los campos y se los pasa al controlador.
     Según el resultado, abre la ventana correspondiente al rol.
     */
    private void validarLogin() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtContrasenia.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Completá los dos campos para ingresar.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Se consulta al controlador para validar
        Usuario logueado = controlador.validarUsuario(user, pass);

        if (logueado != null) {
            JOptionPane.showMessageDialog(this,
                    "Bienvenido/a " + logueado.getUsername(),
                    "Ingreso correcto",
                    JOptionPane.INFORMATION_MESSAGE);

            // Redirige a la vista correspondiente al rol
            abrirVentanaPorRol(logueado.getRol());
            dispose(); // Cierra el login

        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos.",
                    "Error de acceso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     Método que abre la ventana asociada al rol del usuario.
     Aplica el concepto de polimorfismo (cada rol accede a su propia vista).
     */
    private void abrirVentanaPorRol(String rol) {
        JFrame ventana = null;

        switch (rol) {
            case "administrador" -> ventana = new VentanaAdministrador();
            case "veterinario" -> ventana = new VentanaVeterinario();
            case "voluntario" -> ventana = new VentanaVoluntario();
            case "familia" -> ventana = new VentanaFamilia();
            default -> {
                JOptionPane.showMessageDialog(this,
                        "El rol ingresado no está registrado en el sistema.",
                        "Error de rol",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        ventana.setVisible(true);
    }

    /*
     Método main solo para probar la vista aislada.
     En la aplicación real, se llamaría desde el controlador principal.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaLogin().setVisible(true);
        });
    }
}


