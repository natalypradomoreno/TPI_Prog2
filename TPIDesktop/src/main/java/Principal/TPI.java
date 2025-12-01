package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Vista.VentanaLogin;

/*
 clase principal que inicia la aplicacion del sistema de gestion de gatos
 inicializa la conexion JPA y abre la ventana de login
 */
public class TPI {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // inicializa la unidad de persistencia (opcional, fuerza la conexion al inicio)
            emf = Persistence.createEntityManagerFactory("TPIPU");
            em = emf.createEntityManager();
            System.out.println("Conexion JPA inicializada correctamente.");

            // abre la ventana principal de inicio de sesion
            javax.swing.SwingUtilities.invokeLater(() -> {
                new VentanaLogin().setVisible(true);
            });

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicacion: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // cierra la conexion si estaba abierta (por seguridad)
            if (em != null && em.isOpen()) em.close();
            if (emf != null && emf.isOpen()) emf.close();
        }
    }
}


