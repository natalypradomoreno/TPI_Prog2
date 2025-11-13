package Controlador;

import Persistencia.JPAUtil;
import Persistencia.UsuarioJpaController;
import modelo.Usuario;
import javax.persistence.*;
import java.util.List;
import modelo.FamiliaUsuario;

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
            e.printStackTrace();
        }
    }

    /*
     Devuelve la lista completa de usuarios (por si se necesita en el panel del admin).
     */
    public List<Usuario> listarUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    /*
     Valida si el usuario y contraseña existen en la base de datos.
     Si todo está bien, devuelve el objeto Usuario completo.
     Si no, devuelve null.
     */
    public Usuario validarUsuario(String username, String contrasenia) {
        EntityManager em = null;
        Usuario usuario = null;

        try {
            // Se usa el mismo EntityManagerFactory que el resto del proyecto (desde JPAUtil)
            em = JPAUtil.getEMF().createEntityManager();

            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.username = :user AND u.contrasenia = :pass",
                Usuario.class
            );
            query.setParameter("user", username);
            query.setParameter("pass", contrasenia);

            usuario = query.getResultStream().findFirst().orElse(null);

            if (usuario != null) {
                System.out.println("DEBUG (Controlador) >> Usuario encontrado: " + usuario.getUsername());
                System.out.println("DEBUG (Controlador) >> Rol: " + usuario.getRol());
                System.out.println("DEBUG (Controlador) >> ID: " + usuario.getIdUsuario());
            } else {
                System.out.println("DEBUG (Controlador) >> No se encontró usuario con esas credenciales.");
            }

        } catch (Exception e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return usuario;
    }

    /*
     Método auxiliar si más adelante querés validar permisos por rol.
     */
    public boolean esRol(Usuario u, String rolBuscado) {
        return u != null && u.getRol() != null && u.getRol().equalsIgnoreCase(rolBuscado);
    }
    public void modificarUsuario(Usuario u) throws Exception {
    usuarioJpa.edit(u);
}

public void eliminarUsuario(int id) throws Exception {
    usuarioJpa.destroy(id);
}
  // NUEVO: para obtener el objeto familia desde usuario
    public FamiliaUsuario obtenerFamiliaDesdeUsuario(Usuario u) {
        if (u instanceof FamiliaUsuario) {
            return (FamiliaUsuario) u;
        }
        return null;
    }
}
