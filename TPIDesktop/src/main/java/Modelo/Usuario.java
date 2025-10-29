/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package com.principal.TPIWeb.Modelo;

/**
 *
 

public class Usuario {
    private int ID_Usuario;

    /**
     * @return the ID_Usuario
  
    public int getID_Usuario() {
        return ID_Usuario;
    }

    /**
     * @param ID_Usuario the ID_Usuario to set
  
    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }   
}
   */

package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String username;
    private String correo;
    private String contrasenia;
    private int telefono;

    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    private String rol;

    public Usuario() {}

    public Usuario(String username, String correo, String contrasenia, int telefono, Date fechaAlta, String rol) {
        this.username = username;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.rol = rol;
    }

    // Métodos base
    public void registrarse() {}
    public void iniciarSesion() {}
    public void cerrarSesion() {}

    // Getters & Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }
    public Date getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(Date fechaAlta) { this.fechaAlta = fechaAlta; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
