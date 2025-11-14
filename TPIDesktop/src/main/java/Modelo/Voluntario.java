package modelo;

import javax.persistence.*;

/*
 representa al usuario voluntario dentro del sistema
 posee una reputacion segun su participacion
 */
@Entity
@PrimaryKeyJoinColumn(name = "idUsuario")
@Table(name = "voluntario")
public class Voluntario extends Usuario {

    @Column(name = "reputacion")
    private int reputacion;

    public Voluntario() {}

    public Voluntario(String username, String contrasenia, int telefono, String rol, int reputacion) {
        super(username, contrasenia, telefono, rol);
        this.reputacion = reputacion;
    }

    public int getReputacion() { return reputacion; }
    public void setReputacion(int reputacion) { this.reputacion = reputacion; }
        public void modificarReputacion() {}
}
