/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author natal
 */

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 Clase de utilidad para manejar la conexión global a la base de datos mediante JPA.
 Se usa para crear un único EntityManagerFactory que se comparte en todo el proyecto.
 */

public class JPAUtil {

    // Nombre del "persistence-unit" definido en persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "TPIPU";

    // Instancia única (Singleton) del EntityManagerFactory
    private static EntityManagerFactory emf;

    /*
     Devuelve el EntityManagerFactory global.
     Si todavía no está inicializado, lo crea una sola vez.
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
     Cierra la conexión global (por ejemplo, al salir de la aplicación).
     */
    public static void cerrarConexion() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("Conexión JPA cerrada correctamente.");
        }
    }
}
