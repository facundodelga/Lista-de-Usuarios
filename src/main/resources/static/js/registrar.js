// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function registrarUsuarios() {
    let datos = {};


    datos.nombre = document.getElementById('txtNombre').value
    datos.apellido = document.getElementById('txtApellido').value
    datos.email = document.getElementById('txtEmail').value
    datos.telefono = document.getElementById('txtTelefono').value
    datos.password = document.getElementById('txtPassword').value

    let repetirPassword = document.getElementById('txtRPassword').value

    if(repetirPassword != datos.password){
        alert("Contraseñas Distintas");
        return;
    }

    const request = await fetch('api/registrar', {
    method: 'POST',
    headers: getHeaders(),
    body: JSON.stringify(datos)
    });

    alert("Cuenta creada con Exito!");
    window.location.href = 'login.html';
}

function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
   };
}
