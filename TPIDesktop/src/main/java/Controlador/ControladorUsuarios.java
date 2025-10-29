/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

package Controlador;

import Modelo.Persona;
import Persistencia.ControladoraPersistencia;
import java.util.List;

//controladora logica de VListarUsuarios
public class ControladorUsuarios {

    //instacias de las clases involucradas
    Persona Usr = new Persona();
    ControladoraPersistencia CtrlPer = new ControladoraPersistencia();

    //metodos intermediarios entre el controlador de CRUD y la vista
    public List<Persona> LeerUsuarios() {
        return CtrlPer.LeerUsuarios();
    }

    public void EscribirUsuario(String Nombre, String Direccion, String Correo, String Contra) {
        Usr.setNombre(Nombre);
        Usr.setDireccion(Direccion);
        Usr.setCorreo(Correo);
        Usr.setContra(Contra);
        CtrlPer.EscribirUsuario(Usr);
    }

    public void EliminarUsuario(int id) {
        CtrlPer.EliminarUsuario(id);
    }

    public Persona BuscarUsuario(int id) {
        return CtrlPer.BuscarUsuario(id);
    }

    public void EditarUsuario(Persona Usr, String Nombre, String Direccion, String Correo, String Contra) {
        Usr.setNombre(Nombre);
        Usr.setDireccion(Direccion);
        Usr.setCorreo(Correo);
        Usr.setContra(Contra);
        CtrlPer.EditarUsuario(Usr);
    }
}


package Controlador;

import java.util.List;
import modelo.Usuario;
import Persistencia.UsuarioJpaController;
import Persistencia.JPAUtil;

public class ControladorUsuarios {

    private final UsuarioJpaController usuarioJpa;

    public ControladorUsuarios() {
        usuarioJpa = new UsuarioJpaController(JPAUtil.getEMF());
    }

    public void crearUsuario(Usuario u) {
        usuarioJpa.create(u);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }
    // -----------------------------
    // VALIDACIÓN DE LOGIN
    // -----------------------------
    /**
     * Valida si el usuario y contraseña existen en la base de datos.
     * Si coincide, devuelve el objeto Usuario con todos sus datos (incluyendo el rol).
     * Si no coincide, devuelve null.
     * @param username
     * @param contrasenia
     * @return 

    public Usuario validarUsuario(String username, String contrasenia) {
        List<Usuario> lista = usuarioJpa.findUsuarioEntities();
        for (Usuario u : lista) {
            if (u.getUsername().equalsIgnoreCase(username)
                    && u.getContrasenia().equals(contrasenia)) {
                return u; // usuario válido → devuelve el objeto completo
            }
        }
        return null; // no coincide
    }
}
 */

package Controlador;


import Persistencia.JPAUtil;
import Persistencia.UsuarioJpaController;
import java.util.List;
import modelo.Usuario;

/*
 Controlador para manejar los usuarios.
 Se encarga del login y de las operaciones básicas con la tabla usuario.
 */
public class ControladorUsuarios {

    private final UsuarioJpaController usuarioJpa;

    public ControladorUsuarios() {
        // Conectamos el controlador con la capa de persistencia
        usuarioJpa = new UsuarioJpaController(JPAUtil.getEMF());
    }

    /*
     Crea un usuario nuevo en la base de datos.
     */
    public void crearUsuario(Usuario u) {
        try {
            usuarioJpa.create(u);
        } catch (Exception e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        }
    }

    /*
     Devuelve la lista completa de usuarios (por si se necesita en el panel del admin).
     */
    public List<Usuario> listarUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    /*
     Valida si el usuario y contraseña existen.
     Si todo está bien, devuelve el objeto Usuario encontrado.
     Si no, devuelve null.
     */
    public Usuario validarUsuario(String username, String contrasenia) {
        List<Usuario> usuarios = listarUsuarios();

        for (Usuario u : usuarios) {
            // Compara el nombre y contraseña (sin importar mayúsculas)
            if (u.getUsername().equalsIgnoreCase(username) && u.getContrasenia().equals(contrasenia)) {
                System.out.println("Login correcto, rol: " + u.getRol());
                return u;
            }
        }
        System.out.println("Login fallido: usuario o contraseña incorrectos.");
        return null;
    }

    /*
     Este método sirve si más adelante querés validar permisos según el rol.
     */
    public boolean esRol(Usuario u, String rolBuscado) {
        return u != null && u.getRol().equalsIgnoreCase(rolBuscado);
    }
}
