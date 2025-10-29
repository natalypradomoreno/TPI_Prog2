package Principal;

/* Este proyecto sigue el patron de diseño MVC y Utiliza JPA 
para persistir los datos en una base de datos MySQL
Utilizando PHPMyAdmin con XAMP
Nombre de la base de datos: tpipa
Integrantes del grupo:
-Romero, Micaela Denisse
-Toledo,Alejandro Emiliano*/

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Usuario;

public class TPI {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando conexión con JPA...");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            Usuario nuevo = new Usuario();
            nuevo.setUsername("admin");
            nuevo.setContrasenia("root");
            nuevo.setCorreo("admin@test.com");

            em.persist(nuevo);
            em.getTransaction().commit();

            em.close();
            emf.close();

            System.out.println("Conexión exitosa. Usuario guardado en la base.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
