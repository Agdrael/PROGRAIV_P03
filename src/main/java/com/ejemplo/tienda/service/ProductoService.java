package com.ejemplo.tienda.service;

import com.ejemplo.tienda.model.Producto;
import com.ejemplo.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;
//librerias para exepciones estandar de Spring
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

// Inyección de dependencias por constructor
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    //metodo que ejecuta una consulta SELECT * FROM a la tabla productos y devuelve una lista.
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    //metodo que ejecuta una consulta SELECT "X" WHERE id = ?, envuelve el resultado en Optional si no existe queda vacio y tira una exepciones estandar de Spring.
    public Optional<Producto> obtenerProductoPorId(Long id) {
        if (productoRepository.existsById(id)) {
            return productoRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: " + id);
        }
    }

    //metodo que comprueba que el Producto exista si no existe tira una exepciones estandar de Spring.
    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no existe: " + id);
        }
    }
    
    // método para agregar Producto a la base de datos
    public void crearProducto(Producto producto) {
        try {
            productoRepository.getTransaction().begin();
            productoRepository.persist();
            productoRepository.getTransaction().commit();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al crear el producto: " + e.getMessage(), e);
        }
    }

/*


}
