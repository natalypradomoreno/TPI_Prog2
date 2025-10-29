package modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "familia_usuario")
public class FamiliaUsuario extends Usuario implements Serializable {

    private int reputacion;

    public FamiliaUsuario() {}

    public int getReputacion() { return reputacion; }
    public void setReputacion(int reputacion) { this.reputacion = reputacion; }

    public void modificarReputacion() {}
    public void postularseAdopcion() {}
}
