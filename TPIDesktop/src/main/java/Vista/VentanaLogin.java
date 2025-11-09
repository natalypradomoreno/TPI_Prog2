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
import modelo.Veterinario;
import modelo.Voluntario;
import modelo.FamiliaUsuario;
import javax.persistence.*;
import javax.swing.*;
import java.awt.*;

/*
 Vista que representa la pantalla de inicio de sesión.
 Forma parte de la capa de Vista dentro del patrón MVC.
 Se comunica con el ControladorUsuarios para validar los datos ingresados.
 */
public class VentanaLogin extends JFrame {

    private final JTextField txtUsuario;
    private final JPasswordField txtContrasenia;
    private final JButton btnIngresar;
    private final ControladorUsuarios controlador;

    public VentanaLogin() {
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 180);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        controlador = new ControladorUsuarios();

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();

        JLabel lblContrasenia = new JLabel("Contraseña:");
        txtContrasenia = new JPasswordField();

        btnIngresar = new JButton("Iniciar sesión");

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasenia);
        panel.add(txtContrasenia);
        panel.add(new JLabel());
        panel.add(btnIngresar);

        add(panel, BorderLayout.CENTER);

        btnIngresar.addActionListener(e -> validarLogin());
    }

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

        Usuario logueado = controlador.validarUsuario(user, pass);

        if (logueado != null) {
            // --- LOGS DE DEPURACIÓN ---
            System.out.println("DEBUG >> Usuario logueado: " + logueado.getUsername());
            System.out.println("DEBUG >> ID usuario: " + logueado.getIdUsuario());
            System.out.println("DEBUG >> Rol detectado: " + logueado.getRol());
            System.out.println("DEBUG >> ------------------------------------");

            JOptionPane.showMessageDialog(this,
                    "Bienvenido/a " + logueado.getUsername(),
                    "Ingreso correcto",
                    JOptionPane.INFORMATION_MESSAGE);

            abrirVentanaPorRol(logueado);
            dispose();

        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos.",
                    "Error de acceso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirVentanaPorRol(Usuario logueado) {
        String rol = logueado.getRol() != null ? logueado.getRol().trim().toLowerCase() : "";
        System.out.println("DEBUG >> Rol procesado: " + rol);

        JFrame ventana = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
        EntityManager em = emf.createEntityManager();

        try {
            switch (rol) {
                case "administrador" -> {
                    System.out.println("DEBUG >> Abriendo ventana ADMINISTRADOR");
                    ventana = new VentanaAdministrador();
                }

                case "veterinario" -> {
                    System.out.println("DEBUG >> Buscando veterinario con id " + logueado.getIdUsuario());
                    Veterinario vet = em.find(Veterinario.class, logueado.getIdUsuario());
                    System.out.println("DEBUG >> Veterinario encontrado: " + (vet != null));
                    if (vet != null) ventana = new VentanaVeterinario(vet);
                }

                case "voluntario" -> {
                    System.out.println("DEBUG >> Buscando voluntario con id " + logueado.getIdUsuario());
                    Voluntario vol = em.find(Voluntario.class, logueado.getIdUsuario());
                    System.out.println("DEBUG >> Voluntario encontrado: " + (vol != null));
                    if (vol != null) ventana = new VentanaVoluntario(vol);
                }

                case "familia" -> {
                    System.out.println("DEBUG >> Buscando familia con id " + logueado.getIdUsuario());
                    FamiliaUsuario fam = em.find(FamiliaUsuario.class, logueado.getIdUsuario());
                    System.out.println("DEBUG >> Familia encontrada: " + (fam != null));
                    if (fam != null) ventana = new VentanaFamilia(fam);
                }

                default -> {
                    System.out.println("DEBUG >> Rol no reconocido: " + rol);
                    JOptionPane.showMessageDialog(this,
                            "El rol '" + rol + "' no está registrado en el sistema.",
                            "Error de rol",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (ventana != null) {
                ventana.setVisible(true);
                System.out.println("DEBUG >> Ventana abierta correctamente.");
            } else {
                System.out.println("DEBUG >> No se abrió ninguna ventana. Verificá los datos del usuario.");
            }

        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Error al abrir la ventana: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}
