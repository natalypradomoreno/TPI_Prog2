package Controlador;

import Persistencia.JPAUtil;
import Persistencia.ZonaJpaController;
import modelo.Gato;
import modelo.Zona;

import javax.persistence.*;
import java.util.List;

/**
 * Controlador para gestionar las zonas del sistema.
 * Permite crear nuevas zonas, listar zonas y obtener gatos por zona.
 */
public class ControladorZonas {

    private final ZonaJpaController zonaJpa;

    public ControladorZonas() {
        zonaJpa = new ZonaJpaController(JPAUtil.getEMF());
    }

    /**
     * Crea una nueva zona en la base de datos.
     * @param nombre nombre de la zona
     * @param ubicacionGPS coordenadas GPS o descripción de ubicación
     * @return true si se guardó correctamente, false si hubo error
     */
    public boolean crearZona(String nombre, String ubicacionGPS) {
        try {
            Zona z = new Zona();
            z.setNombreZona(nombre);
            z.setUbicacionGPS(ubicacionGPS);
            zonaJpa.create(z);
            return true;
        } catch (Exception e) {
            System.err.println("Error al crear zona: " + e.getMessage());
            return false;
        }
    }

    /**
     * Devuelve la lista de todas las zonas registradas.
     * (alias más legible para el uso en la vista)
     */
    public List<Zona> listarZonas() {
        return zonaJpa.findZonaEntities();
    }

    /**
     * Versión anterior del método, mantenida por compatibilidad.
     */
    public List<Zona> obtenerZonas() {
        return zonaJpa.findZonaEntities();
    }

    /**
     * Devuelve la lista de gatos que pertenecen a una zona específica.
     * @param idZona ID de la zona
     * @return lista de gatos asociados a esa zona
     */
    public List<Gato> obtenerGatosPorZona(int idZona) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
        EntityManager em = emf.createEntityManager();
        List<Gato> gatos = em.createQuery(
                "SELECT g FROM Gato g WHERE g.zona.idZona = :id", Gato.class)
                .setParameter("id", idZona)
                .getResultList();
        em.close();
        emf.close();
        return gatos;
    }
}

