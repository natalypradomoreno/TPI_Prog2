package modelo;

import javax.persistence.*;

/*
 representa al usuario con rol de admin
 hereda de Usuario usando la estrategia JOINED
 se vincula con la tabla administrador que tiene una columna PERMISOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "idUsuario")
@Table(name = "administrador")
public class Administrador extends Usuario {

    @Column(name = "PERMISOS")
    private String permisos;

    public Administrador() {}

    public Administrador(String username, String contrasenia, int telefono, String rol, String permisos) {
        super(username, contrasenia, telefono, rol);
        this.permisos = permisos;
    }

    public String getPermisos() { return permisos; }
    public void setPermisos(String permisos) { this.permisos = permisos; }
    public void eliminarUsuario() {}
}

