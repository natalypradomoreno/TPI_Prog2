/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author natal
 */

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

    // ---- RELACIÓN CON HOGAR ----
    @ManyToOne
    @JoinColumn(name = "idhogar", nullable = false)
    private Hogar hogar;

    // ---- RELACIÓN CON GATO ----
    @ManyToOne
    @JoinColumn(name = "codigoQR", nullable = false)
    private Gato gato;

    // ---- RELACIÓN CON FAMILIA USUARIO ----
    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private FamiliaUsuario usuario;

    // ---- DATOS DE LA POSTULACIÓN ----
    @Column(name = "tipo_postulacion")
    private int tipoPostulacion;  
    // 1 = tránsito
    // 2 = adoptiva

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private int estado;  
    // 0 = pendiente
    // 1 = aceptada
    // 2 = rechazada (si después querés usarlo)

    public Postulacion() {}

    // ====== GETTERS & SETTERS ======

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

