/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author natal
 */

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

    // Relación hacia familiausuario
    @OneToMany(mappedBy = "hogar")
    private List<FamiliaUsuario> familias;

    public Hogar() {}

    // GETTERS & SETTERS

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
