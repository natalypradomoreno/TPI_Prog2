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


/**
 * Clase para manejar la conexión con la base de datos usando JPA.
 * Básicamente, es la que se encarga de abrir y cerrar la "puerta" a la BD 💻
 */
public class JPAUtil {

    // Nombre de la unidad de persistencia (tiene que ser el mismo que pusimos en el persistence.xml)
    private static final String PERSISTENCE_UNIT_NAME = "TPIPU";

    // Acá guardamos la fábrica (la que crea los "EntityManager", o sea los que charlan con la BD)
    private static EntityManagerFactory emf;

    /**
     * Este método devuelve la fábrica de conexiones (EntityManagerFactory)
     * Si todavía no existe, la crea una sola vez y después la reutiliza.
     * Así no estamos abriendo mil conexiones al pedo cada vez que usamos la BD 😅
     * @return 
     */
    public static EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            System.out.println("Conexión JPA inicializada con éxito (TPIPU).");
        }
        return emf;
    }

    /**
     * Este método cierra la fábrica cuando ya no la necesitamos más.
     * Es como decirle: “che, listo, terminamos, cerrá todo y andate a dormir 😴”
     */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("🔒 Conexión JPA cerrada correctamente.");
        }
    }
}
