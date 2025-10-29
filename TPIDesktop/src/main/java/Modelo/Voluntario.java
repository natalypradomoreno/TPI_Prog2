package modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "voluntario")
public class Voluntario extends Usuario implements Serializable {

    private int reputacion;

    public Voluntario() {}

    public int getReputacion() { return reputacion; }
    public void setReputacion(int reputacion) { this.reputacion = reputacion; }

    public void modificarReputacion() {}
}
