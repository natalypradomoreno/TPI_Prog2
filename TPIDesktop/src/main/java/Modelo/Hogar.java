package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "hogar")
public class Hogar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhogar")
    private int idHogar;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "tipo")
    private String tipo;

    // relacion hacia familiausuario
    @OneToMany(mappedBy = "hogar")
    private List<FamiliaUsuario> familias;

    public Hogar() {}

    // getter y setter

    public int getIdHogar() {
        return idHogar;
    }

    public void setIdHogar(int idHogar) {
        this.idHogar = idHogar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<FamiliaUsuario> getFamilias() {
        return familias;
    }

    public void setFamilias(List<FamiliaUsuario> familias) {
        this.familias = familias;
    }
}
