package Persistencia;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 clase de utilidad para manejar la conexion global a la base de datos mediante jpa
 se usa para crear un unico EntityManagerFactory que se comparte en todo el proyecto
 */

public class JPAUtil {

    // nombre del "persistence-unit" definido en persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "TPIPU";

    // instancia unica (Singleton) del EntityManagerFactory
    private static EntityManagerFactory emf;

    /*
     devuelve el EntityManagerFactory global
     si todavia no esta inicializado, lo crea una sola vez
     */
    public static EntityManagerFactory getEMF() {
        if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                System.out.println("Conexión JPA inicializada correctamente (" + PERSISTENCE_UNIT_NAME + ").");
            } catch (Exception e) {
                System.out.println("Error al inicializar la conexión JPA: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return emf;
    }

    /*
     cierra la conexion global (por ejemplo al salir de la aplicacion)
     */
    public static void cerrarConexion() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("Conexión JPA cerrada correctamente.");
        }
    }
}
