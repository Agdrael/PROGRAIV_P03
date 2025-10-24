package com.ejemplo.tienda.service;

import com.ejemplo.tienda.model.Producto;
import com.ejemplo.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        existente.setNombre(producto.getNombre());
        existente.setSku(producto.getSku());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setVencimiento(producto.getVencimiento());
        existente.setCategoria(producto.getCategoria());

        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no existe: " + id);
        }
        productoRepository.deleteById(id);
    }
}
