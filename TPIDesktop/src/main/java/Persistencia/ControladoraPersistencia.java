package Persistencia;

import modelo.*;

public class ControladoraPersistencia {

    private final UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAUtil.getEMF());
    private final AdministradorJpaController adminJpa = new AdministradorJpaController(JPAUtil.getEMF());
    private final VeterinarioJpaController veterinarioJpa = new VeterinarioJpaController(JPAUtil.getEMF());
    private final VoluntarioJpaController voluntarioJpa = new VoluntarioJpaController(JPAUtil.getEMF());
    private final FamiliaUsuarioJpaController familiaJpa = new FamiliaUsuarioJpaController(JPAUtil.getEMF());
    private final GatoJpaController gatoJpa = new GatoJpaController(JPAUtil.getEMF());
    private final HistorialMedicoJpaController historialJpa = new HistorialMedicoJpaController(JPAUtil.getEMF());
    private final TratamientoJpaController tratamientoJpa = new TratamientoJpaController(JPAUtil.getEMF());
    private final EstudiosJpaController estudiosJpa = new EstudiosJpaController(JPAUtil.getEMF());
    private final TareaJpaController tareaJpa = new TareaJpaController(JPAUtil.getEMF());
    private final ZonaJpaController zonaJpa = new ZonaJpaController(JPAUtil.getEMF());
    private final PuntoJpaController puntoJpa = new PuntoJpaController(JPAUtil.getEMF());
    private final HogarJpaController hogarJpa = new HogarJpaController(JPAUtil.getEMF());
    private final VisitaJpaController visitaJpa = new VisitaJpaController(JPAUtil.getEMF());
    private final CalendarioJpaController calendarioJpa = new CalendarioJpaController(JPAUtil.getEMF());
    private final ReporteJpaController reporteJpa = new ReporteJpaController(JPAUtil.getEMF());

    // Métodos rápidos
    public void crearUsuario(Usuario u) { usuarioJpa.create(u); }
    public void crearGato(Gato g) { gatoJpa.create(g); }
    public void crearVeterinario(Veterinario v) { veterinarioJpa.create(v); }
    public void crearHistorial(HistorialDeAtencion h) { historialJpa.create(h); }
    public void crearTratamiento(Tratamiento t) { tratamientoJpa.create(t); }
    public void crearEstudio(Estudios e) { estudiosJpa.create(e); }
    public void crearTarea(Tarea t) { tareaJpa.create(t); }
    public void crearZona(Zona z) { zonaJpa.create(z); }
}


