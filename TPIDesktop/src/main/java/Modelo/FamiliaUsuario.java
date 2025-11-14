package modelo;
/*
 representa a la familia adoptante del sistema
 tambien tiene reputacion basada en adopciones previas
 */

import javax.persistence.*;

@Entity
@Table(name = "familiausuario")
@PrimaryKeyJoinColumn(name = "idUsuario")   // hereda PK de usuario
public class FamiliaUsuario extends Usuario {

    @Column(name = "reputacion")
    private int reputacion;

    // FK hacia hogar (idhogar en BD)
    @ManyToOne
    @JoinColumn(name = "idhogar")
    private Hogar hogar;

    public FamiliaUsuario() {}

    public FamiliaUsuario(String username, String contrasenia, int telefono, String rol, int reputacion) {
        super(username, contrasenia, telefono, rol);
        this.reputacion = reputacion;
    }

    // getters y setters

    public int getReputacion() {
        return reputacion;
    }

    public void setReputacion(int reputacion) {
        this.reputacion = reputacion;
    }

    public Hogar getHogar() {
        return hogar;
    }

    public void setHogar(Hogar hogar) {
        this.hogar = hogar;
    }

    // metodos logicos 
    public void modificarReputacion() {}
    public void postularseAdopcion() {}
}

