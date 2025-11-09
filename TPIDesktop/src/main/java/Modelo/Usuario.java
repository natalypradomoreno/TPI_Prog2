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
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int idUsuario;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "telefono")
    private int telefono;

    @Column(name = "rol")
    private String rol;

    // Constructor vacío requerido por JPA
    public Usuario() {}

    // Constructor completo (por si se quiere crear un usuario completo)
    public Usuario(String username, String contrasenia, int telefono, String rol) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.rol = rol;
    }

    // Constructor corto (para carga inicial o pruebas)
    public Usuario(String username, String contrasenia, String rol) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    // Métodos base
    public void iniciarSesion() {}
    public void cerrarSesion() {}

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Usuario{" + 
                "idUsuario=" + idUsuario + 
                ", username='" + username + '\'' + 
                ", rol='" + rol + '\'' + 
                '}';
    }
}
