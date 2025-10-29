/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author natal
 */
package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "visita_seguimiento")
public class VisitaDeSeguimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVisita;

    private String ubicacion;
    private String estado; // PENDIENTE o COMPLETADA

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    private Calendario calendario;

    public VisitaDeSeguimiento() {}

    public int getIdVisita() { return idVisita; }
    public void setIdVisita(int idVisita) { this.idVisita = idVisita; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Calendario getCalendario() { return calendario; }
    public void setCalendario(Calendario calendario) { this.calendario = calendario; }
}

