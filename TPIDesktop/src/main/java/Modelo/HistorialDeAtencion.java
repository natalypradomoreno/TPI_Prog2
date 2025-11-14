package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
representa el registro de atencion veterinaria de un gato
se relaciona con el veterinario, el gato, el tratamiento y los estudios realizados
*/
@Entity
@Table(name = "historial_atencion")
public class HistorialDeAtencion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDATENCION")
    private int idAtencion;

    @Column(name = "ARCHIVOADJUNTO", length = 255)
    private String archivoAdjunto;

    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA")
    private Date fecha;

    @Temporal(TemporalType.TIME)
    @Column(name = "HORA")
    private Date hora;

    // muchos historiales pertenecen a un gato
    @ManyToOne
    @JoinColumn(name = "codigoQR", referencedColumnName = "codigoQR")
    private Gato gato;

    // muchos historiales pueden ser atendidos por un mismo veterinario
    @ManyToOne
    @JoinColumn(name = "veterinario_id", referencedColumnName = "idUsuario")
    private Veterinario veterinario;

    // cada historial puede tener un tratamiento asociado
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRATAMIENTO_IDTRATAMIENTO", referencedColumnName = "IDTRATAMIENTO")
    private Tratamiento tratamiento;

    // un historial puede tener varios estudios vinculados
    @OneToMany(mappedBy = "historial", cascade = CascadeType.ALL)
    private List<Estudios> estudios;

    @ManyToOne
    @JoinColumn(name = "ESTUDIO_IDESTUDIO")
    private Estudios estudio;

    public HistorialDeAtencion() {}

    // getters y setters 
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }

    public String getArchivoAdjunto() { return archivoAdjunto; }
    public void setArchivoAdjunto(String archivoAdjunto) { this.archivoAdjunto = archivoAdjunto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Date getHora() { return hora; }
    public void setHora(Date hora) { this.hora = hora; }

    public Gato getGato() { return gato; }
    public void setGato(Gato gato) { this.gato = gato; }

    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }

    public Tratamiento getTratamiento() { return tratamiento; }
    public void setTratamiento(Tratamiento tratamiento) { this.tratamiento = tratamiento; }

    public List<Estudios> getEstudios() { return estudios; }
    public void setEstudios(List<Estudios> estudios) { this.estudios = estudios; }
    
    public Estudios getEstudio() { return estudio; }
    public void setEstudio(Estudios estudio) { this.estudio = estudio; }
}
