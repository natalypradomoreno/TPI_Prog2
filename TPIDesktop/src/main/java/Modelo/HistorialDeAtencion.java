package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "historial_atencion")
public class HistorialDeAtencion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAtencion;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Temporal(TemporalType.TIME)
    private Date hora;

    private String descripcion;
    private String archivoAdjunto;

    @ManyToOne
    @JoinColumn(name = "codigoQR")
    private Gato gato;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @OneToOne(cascade = CascadeType.ALL)
    private Tratamiento tratamiento;

    @OneToMany(mappedBy = "historial", cascade = CascadeType.ALL)
    private List<Estudios> estudios;

    public HistorialDeAtencion() {}

    // Getters & Setters
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Date getHora() { return hora; }
    public void setHora(Date hora) { this.hora = hora; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getArchivoAdjunto() { return archivoAdjunto; }
    public void setArchivoAdjunto(String archivoAdjunto) { this.archivoAdjunto = archivoAdjunto; }
    public Gato getGato() { return gato; }
    public void setGato(Gato gato) { this.gato = gato; }
    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }
    public Tratamiento getTratamiento() { return tratamiento; }
    public void setTratamiento(Tratamiento tratamiento) { this.tratamiento = tratamiento; }
    public List<Estudios> getEstudios() { return estudios; }
    public void setEstudios(List<Estudios> estudios) { this.estudios = estudios; }
}
