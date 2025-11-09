package modelo;

import javax.persistence.*;

/*
 Representa al usuario veterinario del sistema.
 Tiene una matrícula profesional.
 */
@Entity
@PrimaryKeyJoinColumn(name = "idUsuario")
@Table(name = "veterinario")
public class Veterinario extends Usuario {

    @Column(name = "matricula")
    private String matricula;

    public Veterinario() {}

    public Veterinario(String username, String contrasenia, int telefono, String rol, String matricula) {
        super(username, contrasenia, telefono, rol);
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void registrarMatricula() {}
}
