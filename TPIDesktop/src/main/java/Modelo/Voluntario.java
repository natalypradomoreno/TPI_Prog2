package modelo;

import javax.persistence.*;

/*
 Representa al usuario voluntario dentro del sistema.
 Posee una reputación según su participación.
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
