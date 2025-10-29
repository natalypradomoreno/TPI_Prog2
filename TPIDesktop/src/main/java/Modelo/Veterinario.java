package modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "veterinario")
public class Veterinario extends Usuario implements Serializable {

    private String matricula;

    public Veterinario() {}

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public void registrarMatricula() {}
}
