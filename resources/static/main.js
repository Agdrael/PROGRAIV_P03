// URL base del backend Spring Boot
const API_URL = "http://localhost:8080/api/productos";

let editingId = null;
const form = document.getElementById("product-form");
const tableBody = document.querySelector("#product-table tbody");
const submitBtn = document.getElementById("submit-btn");

document.addEventListener("DOMContentLoaded", listarProductos);


// Manejo del formulario
form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const producto = {
        nombre: document.getElementById("nombre").value.trim(),
        sku: document.getElementById("sku").value.trim(),
        descripcion: document.getElementById("descripcion").value.trim(),
        precio: parseFloat(document.getElementById("precio").value),
        vencimiento: document.getElementById("vencimiento").value,
        categoria: document.getElementById("categoria").value
    };

    if (!producto.nombre || !producto.sku || !producto.descripcion ||
        !producto.precio || !producto.vencimiento || !producto.categoria) {
        alert("completa todos los campos antes de enviar");
        return;
    }

    try {
        if (editingId === null) {
            await crearProducto(producto);
        } else {
            await actualizarProducto(editingId, producto);
            editingId = null;
            submitBtn.textContent = "Crear Producto";
        }
        form.reset();
        listarProductos();
    } catch (err) {
        console.error("Error al guardar producto:", err);
        alert("error al guardar el producto.");
    }
});


// CRUD API

async function listarProductos() {
    try {
        const res = await fetch(API_URL);
        const productos = await res.json();
        renderTabla(productos);
    } catch (err) {
        console.error("Error al listar productos:", err);
        alert("No se pudo cargar la lista de productos");
    }
}

async function crearProducto(producto) {
    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(producto)
    });
}

async function actualizarProducto(id, producto) {
    await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(producto)
    });
}

async function eliminarProducto(id) {
    if (!confirm("Seguro que deseas eliminar este producto?")) return;

    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    listarProductos();
}

// Renderizado de la tabla
function renderTabla(productos) {
    tableBody.innerHTML = "";

    if (productos.length === 0) {
        const fila = document.createElement("tr");
        fila.innerHTML = `<td colspan="7" class="text-center">No hay productos registrados</td>`;
        tableBody.appendChild(fila);
        return;
    }

    productos.forEach(producto => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${producto.nombre}</td>
            <td>${producto.sku}</td>
            <td>${producto.descripcion}</td>
            <td>$${producto.precio.toFixed(2)}</td>
            <td>${producto.vencimiento}</td>
            <td>${producto.categoria}</td>
            <td class="actions">
                <button class="edit-btn" onclick="editarProducto(${producto.id})">Editar</button>
                <button class="delete-btn" onclick="eliminarProducto(${producto.id})">Eliminar</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Editar

async function editarProducto(id) {
    try {
        const res = await fetch(`${API_URL}/${id}`);
        const producto = await res.json();

        document.getElementById("nombre").value = producto.nombre;
        document.getElementById("sku").value = producto.sku;
        document.getElementById("descripcion").value = producto.descripcion;
        document.getElementById("precio").value = producto.precio;
        document.getElementById("vencimiento").value = producto.vencimiento;
        document.getElementById("categoria").value = producto.categoria;

        editingId = id;
        submitBtn.textContent = "Actualizar Producto";
    } catch (err) {
        console.error("Error al editar producto:", err);
        alert("No se pudo cargar la informacion del producto");
    }
}
