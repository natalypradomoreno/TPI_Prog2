package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Vista.VentanaLogin;

/*
 Clase principal que inicia la aplicación del sistema de gestión de gatos.
 Inicializa la conexión JPA (unidad de persistencia TPIPU) y abre la ventana de login.
 */
public class TPI {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // Inicializa la unidad de persistencia (opcional, fuerza la conexión al inicio)
            emf = Persistence.createEntityManagerFactory("TPIPU");
            em = emf.createEntityManager();
            System.out.println("Conexión JPA inicializada correctamente.");

            // Abre la ventana principal de inicio de sesión
            javax.swing.SwingUtilities.invokeLater(() -> {
                new VentanaLogin().setVisible(true);
            });

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cierra la conexión si estaba abierta (por seguridad)
            if (em != null && em.isOpen()) em.close();
            if (emf != null && emf.isOpen()) emf.close();
        }
    }
}


