/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */

package com.ejemplo.tienda.controller;

import com.ejemplo.tienda.model.Producto;
import com.ejemplo.tienda.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author jairo
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController{

    private final ProductoService productoService;

    // Inyección de dependencias por constructor
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
     // GET: listar todos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // GET: buscar por ID
    @GetMapping("/{id}")
    public Optional<Producto> obtenerUsuario(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }
    
     // DELETE: eliminar usuario
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "Producto eliminado con éxito.";
    }
}