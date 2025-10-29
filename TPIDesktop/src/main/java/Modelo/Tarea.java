package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarea;

    private String tipoTarea;
    private String descripcion;
    private String ubicacion;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    private Date hora;

    private String estado; // SIN_ASIGNAR, ASIGNADO, FINALIZADO

    @ManyToOne
    private Voluntario voluntario;

    @ManyToOne
    private Gato gato;

    public Tarea() {}

    // Getters & Setters
    public int getIdTarea() { return idTarea; }
    public void setIdTarea(int idTarea) { this.idTarea = idTarea; }
    public String getTipoTarea() { return tipoTarea; }
    public void setTipoTarea(String tipoTarea) { this.tipoTarea = tipoTarea; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Date getHora() { return hora; }
    public void setHora(Date hora) { this.hora = hora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Voluntario getVoluntario() { return voluntario; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }
    public Gato getGato() { return gato; }
    public void setGato(Gato gato) { this.gato = gato; }
}
