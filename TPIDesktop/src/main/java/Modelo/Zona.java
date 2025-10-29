package modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "zona")
public class Zona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idZona;

    private String nombreZona;
    private String ubicacionGPS;

    public Zona() {}

    public int getIdZona() { return idZona; }
    public void setIdZona(int idZona) { this.idZona = idZona; }
    public String getNombreZona() { return nombreZona; }
    public void setNombreZona(String nombreZona) { this.nombreZona = nombreZona; }
    public String getUbicacionGPS() { return ubicacionGPS; }
    public void setUbicacionGPS(String ubicacionGPS) { this.ubicacionGPS = ubicacionGPS; }
}
