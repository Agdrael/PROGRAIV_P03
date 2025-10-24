package com.ejemplo.tienda.service;

import com.ejemplo.tienda.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listar();
    Producto buscarPorId(Integer id);
    Producto crear(Producto producto);
    Producto actualizar(Integer id, Producto producto);
    void eliminar(Integer id);
}
