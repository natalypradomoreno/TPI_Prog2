package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTAREA")
    private int idTarea;

    @Column(name = "TIPOTAREA")
    private String tipoTarea;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "UBICACION")
    private String ubicacion;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "HORA")
    private Time hora;

    @Column(name = "ESTADO")
    private String estado;

    // Estas dos columnas están mapeadas directamente en la tabla
    @Column(name = "VOLUNTARIO_IDUSUARIO")
    private int voluntarioIdUsuario;

    @Column(name = "GATO_codigoQR")
    private String gatoCodigoQR;

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

    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getVoluntarioIdUsuario() { return voluntarioIdUsuario; }
    public void setVoluntarioIdUsuario(int voluntarioIdUsuario) { this.voluntarioIdUsuario = voluntarioIdUsuario; }

    public String getGatoCodigoQR() { return gatoCodigoQR; }
    public void setGatoCodigoQR(String gatoCodigoQR) { this.gatoCodigoQR = gatoCodigoQR; }
}
