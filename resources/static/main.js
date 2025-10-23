/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
let editingIndex = -1;
const form = document.getElementById('product-form');
const tableBody = document.querySelector('#product-table tbody');
const submitBtn = document.getElementById('submit-btn');

form.addEventListener('submit', function (e) {
    e.preventDefault();
    const product = {
        nombre: document.getElementById('nombre').value,
        sku: document.getElementById('sku').value,
        descripcion: document.getElementById('descripcion').value,
        precio: document.getElementById('precio').value,
        vencimiento: document.getElementById('vencimiento').value,
        categoria: document.getElementById('categoria').value
    };

    if (editingIndex === -1) {
        addProduct(product);
    } else {
        updateProduct(editingIndex, product);
        editingIndex = -1;
        submitBtn.textContent = 'Crear Producto';
    }

    form.reset();
});

function addProduct(product) {
    const row = createRow(product);
    tableBody.appendChild(row);
}

function updateProduct(index, product) {
    const rows = tableBody.querySelectorAll('tr');
    const row = createRow(product);
    tableBody.replaceChild(row, rows[index]);
}

function createRow(product) {
    const row = document.createElement('tr');
    row.innerHTML = `
                <td>${product.nombre}</td>
                <td>${product.sku}</td>
                <td>${product.descripcion}</td>
                <td>$${product.precio}</td>
                <td>${product.vencimiento}</td>
                <td>${product.categoria}</td>
                <td class="actions">
                    <button class="edit-btn" onclick="editProduct(this)">Editar</button>
                    <button class="delete-btn" onclick="deleteProduct(this)">Eliminar</button>
                </td>
            `;
    return row;
}

function editProduct(btn) {
    const row = btn.closest('tr');
    const cells = row.querySelectorAll('td');
    document.getElementById('nombre').value = cells[0].textContent;
    document.getElementById('sku').value = cells[1].textContent;
    document.getElementById('descripcion').value = cells[2].textContent;
    document.getElementById('precio').value = cells[3].textContent.replace('$', '');
    document.getElementById('vencimiento').value = cells[4].textContent;
    document.getElementById('categoria').value = cells[5].textContent;
    editingIndex = Array.from(tableBody.children).indexOf(row);
    submitBtn.textContent = 'Actualizar Producto';
}

function deleteProduct(btn) {
    const row = btn.closest('tr');
    tableBody.removeChild(row);
}

