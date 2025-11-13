package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 esta clase representa una visita de seguimiento que hace el voluntario.
 es basicamente el modelo que jpa usa para mapear los datos de la tabla
 visita_seguimiento en la base. no tiene logica pesada, solo guarda info.
*/
@Entity
@Table(name = "visita_seguimiento")
public class VisitaDeSeguimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVisita;  
    // id autogenerado por mysql, jpa lo maneja solo

    private String ubicacion;  
    // lugar donde se hizo la visita

    private String estado;  
    // puede ser Pendiente o Realizada

    @Temporal(TemporalType.DATE)
    private Date fecha;  
    // solo guardamos fecha (dia/mes/anio) porque la hora no la usamos para nada

    @ManyToOne
    private Calendario calendario;  
    /*
     relacion con el calendario. esto viene del modelo original del tpi.
     muchas visitas pueden usar el mismo registro de calendario.
    */

    @ManyToOne
    @JoinColumn(name = "HOGAR_IDHOGAR")
    private Hogar hogar;
    /*
     esta parte es la nueva que agregamos.
     ahora la visita esta asociada a un hogar especifico.
     esto permite despues mostrar la direccion del hogar sin hacer consultas extras.
    */

    public VisitaDeSeguimiento() {}

    // getters y setters sin ninguna logica especial
    public int getIdVisita() { return idVisita; }
    public void setIdVisita(int idVisita) { this.idVisita = idVisita; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Calendario getCalendario() { return calendario; }
    public void setCalendario(Calendario calendario) { this.calendario = calendario; }

    public Hogar getHogar() { return hogar; }
    public void setHogar(Hogar hogar) { this.hogar = hogar; }
}
