/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
/**
 *
 * @author Max
 
@Entity
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String nombre_producto;
    private String descripcion;
    private String categoria;
    private float precio;
    private int stock;
    private String imagen;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Articulo> articulos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Detalle_de_pedido> detalle_de_pedido;

    public Producto() {
    }

    public Producto(List<Articulo> articulos, String categoria, String descripcion, List<Detalle_de_pedido> detalle_de_pedido, int id, String imagen, String nombre_producto, float precio, int stock) {
        this.articulos = articulos;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.detalle_de_pedido = detalle_de_pedido;
        this.id = id;
        this.imagen = imagen;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.stock = stock;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("id=").append(id);
        sb.append(", nombre_producto=").append(nombre_producto);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", categoria=").append(categoria);
        sb.append(", precio=").append(precio);
        sb.append(", stock=").append(stock);
        sb.append(", imagen=").append(imagen);
        sb.append(", articulos=").append(articulos);
        sb.append('}');
        return sb.toString();
    }

    public List<Detalle_de_pedido> getDetalle_de_pedido() {
        return detalle_de_pedido;
    }

    public void setDetalle_de_pedido(List<Detalle_de_pedido> detalle_de_pedido) {
        this.detalle_de_pedido = detalle_de_pedido;
    }
    

}
 */

package modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "gato")
public class Gato implements Serializable {

    @Id
    @Column(name = "codigoQR")
    private String codigoQR; // Es el ID

    private String nombre;
    private String raza;
    private String color;
    private String caracteristicas;
    private String estado;
    private boolean certificadoAptitud;

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
    public boolean isCertificadoAptitud() { return certificadoAptitud; }
    public void setCertificadoAptitud(boolean certificadoAptitud) { this.certificadoAptitud = certificadoAptitud; }
}
