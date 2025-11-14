package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 representa a un gato identificado por un codigo QR unico
 cada gato pertenece a una zona (relacion N:1 con zona)
 */
@Entity
@Table(name = "gato")
public class Gato implements Serializable {

    @Id
    @Column(name = "codigoQR")
    private String codigoQR; // es el id

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "raza")
    private String raza;

    @Column(name = "color")
    private String color;

    @Column(name = "caracteristicas")
    private String caracteristicas;

    @Column(name = "estado")
    private String estado;
    
    @Column(name = "situacion")
private int situacion; // 0: adopcion, 1: transito, 2: adoptado


    @Column(name = "certificadoAptitud")
    private boolean certificadoAptitud;

    // relacion N a 1. muchos gatos pueden pertenecer a una misma zona
    @ManyToOne
    @JoinColumn(name = "idZona") // FK que une gato con zona
    private Zona zona;
    

    // relacion 1 a N con el historial del gato (ya existente)
    @OneToMany(mappedBy = "gato", cascade = CascadeType.ALL)
    private List<HistorialDeAtencion> historiales;

    public Gato() {}

    // Getters & Setters
    public String getCodigoQR() { return codigoQR; }
    public void setCodigoQR(String codigoQR) { this.codigoQR = codigoQR; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public int getSituacion() {return situacion;}
    public void setSituacion(int situacion) {this.situacion = situacion;}

    public boolean isCertificadoAptitud() { return certificadoAptitud; }
    public void setCertificadoAptitud(boolean certificadoAptitud) { this.certificadoAptitud = certificadoAptitud; }

    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }

    public List<HistorialDeAtencion> getHistoriales() { return historiales; }
    public void setHistoriales(List<HistorialDeAtencion> historiales) { this.historiales = historiales; }
    
    // dentro de la clase gato (modelo/Gato.java)

    // 0 = en adopcion, 1 = en transito, 2 = adoptado
    private int enTransito;

    // getter y setter
    public int getEnTransito() {
        return enTransito;
    }

    public void setEnTransito(int enTransito) {
        this.enTransito = enTransito;
    }

    @Override
    public String toString() {
        return nombre; // solo muestra el nombre del gato
    }

}

