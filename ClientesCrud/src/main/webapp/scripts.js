document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evitar el envío del formulario de forma predeterminada

    // Obtener los valores del formulario
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const identificacion = document.getElementById('identificacion').value;

    // Crear un objeto FormData con los datos del formulario
    const formData = new FormData();
    formData.append('nombre', nombre);
    formData.append('apellido', apellido);
    formData.append('identificacion', identificacion);

    // Realizar la petición POST al servlet usando fetch
    fetch('http://localhost:8080/ClientesCrud/simpleServlet', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        console.log("Respuesta del servidor:", data);
        mostrarMensaje(data); // Mostrar respuesta en el modal

        // Crear un nuevo elemento de lista para el usuario registrado
        const userItem = document.createElement('li');
        userItem.classList.add('list-group-item');
        userItem.textContent = `Nombre: ${nombre}, Apellido: ${apellido}, Identificación: ${identificacion}`;
        
        // Agregar el nuevo usuario a la lista
        document.getElementById('userList').appendChild(userItem);
        
        // Limpiar el formulario
        document.getElementById('registerForm').reset();
    })
    .catch(error => {
        console.error('Error en la solicitud:', error);
        mostrarMensaje('Error al registrar usuario'); // Mostrar error en el modal
    });
});

function mostrarMensaje(mensaje) {
    document.getElementById('modalMessage').textContent = mensaje;
    const modal = new bootstrap.Modal(document.getElementById('responseModal'));
    modal.show();
}
