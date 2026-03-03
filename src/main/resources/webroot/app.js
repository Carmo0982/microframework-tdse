// Ejemplo de JavaScript estático servido desde webroot
console.log("Microframework TDSE cargado correctamente.");

// Llama al endpoint /pi y muestra el resultado en consola
fetch('/pi')
    .then(response => response.text())
    .then(data => console.log("Valor de PI desde el servidor:", data));

