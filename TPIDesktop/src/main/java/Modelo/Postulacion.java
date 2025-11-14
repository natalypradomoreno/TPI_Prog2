package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "postulacion")
public class Postulacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpostulacion")
    private int idPostulacion;

    // relacion con hogar
    @ManyToOne
    @JoinColumn(name = "idhogar", nullable = false)
    private Hogar hogar;

    // relacion con gato
    @ManyToOne
    @JoinColumn(name = "codigoQR", nullable = false)
    private Gato gato;

    // relacion con familia usuario
    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private FamiliaUsuario usuario;

    //datos de la postulacion
    @Column(name = "tipo_postulacion")
    private int tipoPostulacion;  
    // 1 = transito
    // 2 = adoptiva

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private int estado;  
    // 0 = pendiente
    // 1 = aceptada
    // 2 = rechazada (si despues queres usarlo)

    public Postulacion() {}

    // getters y setters

    public int getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(int idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Hogar getHogar() {
        return hogar;
    }

    public void setHogar(Hogar hogar) {
        this.hogar = hogar;
    }

    public Gato getGato() {
        return gato;
    }

    public void setGato(Gato gato) {
        this.gato = gato;
    }

    public FamiliaUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(FamiliaUsuario usuario) {
        this.usuario = usuario;
    }

    public int getTipoPostulacion() {
        return tipoPostulacion;
    }

    public void setTipoPostulacion(int tipoPostulacion) {
        this.tipoPostulacion = tipoPostulacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

@Override
public String toString() {
    return "Gato: " + gato.getNombre() + "  |  Familia: " + usuario.getUsername();
}

    
}

