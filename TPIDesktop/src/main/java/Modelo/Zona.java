package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 Representa una zona geográfica donde se encuentra un grupo de gatos.
 Cada zona puede tener varios gatos asociados (relación 1:N).
 */
@Entity
@Table(name = "zona")
public class Zona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idZona")
    private int idZona;

    @Column(name = "nombreZona")
    private String nombreZona;

    @Column(name = "ubicacionGPS")
    private String ubicacionGPS;

    // Relación 1 a N: una zona puede tener muchos gatos
    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL)
    private List<Gato> gatos;

    public Zona() {}

    // Getters & Setters
    public int getIdZona() { return idZona; }
    public void setIdZona(int idZona) { this.idZona = idZona; }

    public String getNombreZona() { return nombreZona; }
    public void setNombreZona(String nombreZona) { this.nombreZona = nombreZona; }

    public String getUbicacionGPS() { return ubicacionGPS; }
    public void setUbicacionGPS(String ubicacionGPS) { this.ubicacionGPS = ubicacionGPS; }

    public List<Gato> getGatos() { return gatos; }
    public void setGatos(List<Gato> gatos) { this.gatos = gatos; }

    @Override
public String toString() {
    return nombreZona;
}



}

