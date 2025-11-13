package modelo;
/*
 Representa a la familia adoptante del sistema.
 También tiene reputación basada en adopciones previas.
 */



import javax.persistence.*;

@Entity
@Table(name = "familiausuario")
@PrimaryKeyJoinColumn(name = "idUsuario")   // hereda PK de Usuario
public class FamiliaUsuario extends Usuario {

    @Column(name = "reputacion")
    private int reputacion;

    // FK hacia Hogar (idhogar en BD)
    @ManyToOne
    @JoinColumn(name = "idhogar")
    private Hogar hogar;

    public FamiliaUsuario() {}

    public FamiliaUsuario(String username, String contrasenia, int telefono, String rol, int reputacion) {
        super(username, contrasenia, telefono, rol);
        this.reputacion = reputacion;
    }

    // ----- GETTERS & SETTERS -----

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

    // Métodos lógicos (si más adelante los usás realmente)
    public void modificarReputacion() {}
    public void postularseAdopcion() {}
}

