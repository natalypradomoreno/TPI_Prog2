package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reporte")
public class Reporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReporte;

    private String tipoReporte;
    private String descripcion;
    private int cantidadGatos;

    @Temporal(TemporalType.DATE)
    private Date fechaReporte;

    @ManyToOne
    private Zona zona;

    public Reporte() {}

    public int getIdReporte() { return idReporte; }
    public void setIdReporte(int idReporte) { this.idReporte = idReporte; }
    public String getTipoReporte() { return tipoReporte; }
    public void setTipoReporte(String tipoReporte) { this.tipoReporte = tipoReporte; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getCantidadGatos() { return cantidadGatos; }
    public void setCantidadGatos(int cantidadGatos) { this.cantidadGatos = cantidadGatos; }
    public Date getFechaReporte() { return fechaReporte; }
    public void setFechaReporte(Date fechaReporte) { this.fechaReporte = fechaReporte; }
    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }
}

