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

@Entity
@Table(name = "punto_avistamiento")
public class PuntoDeAvistamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPunto;

    private String direccion;
    private String ubicacionGPS;

    @ManyToOne
    private Zona zona;

    public PuntoDeAvistamiento() {}

    public int getIdPunto() { return idPunto; }
    public void setIdPunto(int idPunto) { this.idPunto = idPunto; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getUbicacionGPS() { return ubicacionGPS; }
    public void setUbicacionGPS(String ubicacionGPS) { this.ubicacionGPS = ubicacionGPS; }
    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }
}

