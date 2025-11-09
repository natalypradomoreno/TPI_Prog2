/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
package com.principal.TPIWeb.Modelo;

/** *
 * @author Max Saturn

public class Administrador{
    private int ID_Administrador;

    /**
     * @return the ID_Administrador

    public int getID_Administrador() {
        return ID_Administrador;
    }

    /**
     * @param ID_Administrador the ID_Administrador to set

    public void setID_Administrador(int ID_Administrador) {
        this.ID_Administrador = ID_Administrador;
    }
     */
package modelo;

import javax.persistence.*;

/*
 Representa al usuario con rol de Administrador.
 Hereda de Usuario usando la estrategia JOINED.
 Se vincula con la tabla 'administrador', que tiene una columna PERMISOS.
 */
@Entity
@PrimaryKeyJoinColumn(name = "idUsuario")
@Table(name = "administrador")
public class Administrador extends Usuario {

    @Column(name = "PERMISOS")
    private String permisos;

    public Administrador() {}

    public Administrador(String username, String contrasenia, int telefono, String rol, String permisos) {
        super(username, contrasenia, telefono, rol);
        this.permisos = permisos;
    }

    public String getPermisos() { return permisos; }
    public void setPermisos(String permisos) { this.permisos = permisos; }
    public void eliminarUsuario() {}
}

