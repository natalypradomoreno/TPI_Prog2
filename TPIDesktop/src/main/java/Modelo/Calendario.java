package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "calendario")
public class Calendario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCalendario;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Calendario() {}

    public int getIdCalendario() { return idCalendario; }
    public void setIdCalendario(int idCalendario) { this.idCalendario = idCalendario; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
