package com.ejemplo.tienda.model;

import java.time.LocalDate; //inportar para poder utilizar el tipo de dato Date;
import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String sku;
    private String descripcion;
    private double precio;
    private LocalDate vencimiento;
    private String categoria;

    // Constructor vacío (obligatorio para JPA)
    public Producto() {

    }

    // Constructor con parámetros
    public Producto(int id, String nombre, String sku, String descripcion, double precio, LocalDate vencimiento, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.sku = sku;
        this.descripcion = descripcion;
        this.precio = precio;
        this.vencimiento = vencimiento;
        this.categoria = categoria;
    }

    //metodos get y set 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
