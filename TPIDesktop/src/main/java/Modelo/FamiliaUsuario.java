package modelo;

import javax.persistence.*;

/*
 Representa a la familia adoptante del sistema.
 También tiene reputación basada en adopciones previas.
 */
@Entity
@PrimaryKeyJoinColumn(name = "idUsuario")
@Table(name = "familiausuario")
public class FamiliaUsuario extends Usuario {

    @Column(name = "reputacion")
    private int reputacion;

    public FamiliaUsuario() {}

    public FamiliaUsuario(String username, String contrasenia, int telefono, String rol, int reputacion) {
        super(username, contrasenia, telefono, rol);
        this.reputacion = reputacion;
    }

      public int getReputacion() { return reputacion; }
    public void setReputacion(int reputacion) { this.reputacion = reputacion; }

    public void modificarReputacion() {}
    public void postularseAdopcion() {}
}
