package modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estudios")
public class Estudios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstudio;

    private String nombreEstudio;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "historial_id")
    private HistorialDeAtencion historial;

    public Estudios() {}

    // Getters & Setters
    public int getIdEstudio() { return idEstudio; }
    public void setIdEstudio(int idEstudio) { this.idEstudio = idEstudio; }
    public String getNombreEstudio() { return nombreEstudio; }
    public void setNombreEstudio(String nombreEstudio) { this.nombreEstudio = nombreEstudio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public HistorialDeAtencion getHistorial() { return historial; }
    public void setHistorial(HistorialDeAtencion historial) { this.historial = historial; }
}
