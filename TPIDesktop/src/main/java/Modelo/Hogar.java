package modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hogar")
public class Hogar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHogar;

    private String direccion;
    private String tipo;

    @ManyToOne
    private FamiliaUsuario familia;

    public Hogar() {}

    public int getIdHogar() { return idHogar; }
    public void setIdHogar(int idHogar) { this.idHogar = idHogar; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public FamiliaUsuario getFamilia() { return familia; }
    public void setFamilia(FamiliaUsuario familia) { this.familia = familia; }
}
