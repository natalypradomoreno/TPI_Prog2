/*
 clase entidad calendario. EN PROGRESO
 esta clase basicamente representa la tabla calendario de la base.
 la usamos para guardar la fecha de una visita, porque la estructura original
 del proyecto separa fecha en una tabla aparte.

 no tiene logica ni comportamientos, es solo un modelo simple que jpa mapea contra la tabla.
*/
package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "calendario")
public class Calendario implements Serializable {

    /*
     id autogenerado por la base. cada vez que se crea un calendario nuevo
     la base se encarga de asignarle el id.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCalendario;

    /*
     fecha guardada como tipo date. aca jpa usa temporal date para que
     no se guarde hora ni minutos, solo la fecha.
    */
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Calendario() {}

    // getters y setters clasicos para que jpa pueda leer y escribir los datos
    public int getIdCalendario() { return idCalendario; }
    public void setIdCalendario(int idCalendario) { this.idCalendario = idCalendario; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
