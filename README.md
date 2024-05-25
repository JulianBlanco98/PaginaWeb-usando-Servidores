# PaginaWeb-usando-Servidores

Proyecto final de la asignatura de Tecnologías Web del 3 año de Ingeniería Informática

## Descripción

El proyecto consiste en crear una aplicación web para la gestión de rutas de caminos en Extremadura.

La aplicación tiene 2 diferentes versiones: 

- **Usuario**: cualquier persona que se registre y se logueé puede acceder a ver las rutas, reservar una o varias rutas y gestionar sus reservas.

- **Administrador**: el administrador puede tanto gestionar las rutas (crear, actualizar o eliminar las rutas) como los usuarios (crear, editarlos o borrarlos).

## Ejecución de la práctica

Dependiendo del puerto donde se tenga instalado el Tomcat, la dirección URL cambiará. La dirección es la siguiente:

 ```sh
  http://localhost:8082/EF2_Rutas/
  ```

## Tecnologías Usadas

### Java EE
Tecnología usada para crear el proyecto siguiendo el modelo Cliente - Servidor (petición/respuesta)

### Maria DB (Dbeaver)
Base de Datos relacional donde se configura la conexión en el fichero context.xml

![screenshot](Capturas/ER.png)

### Servlet y JSP
Vistas dinámicas de la aplicación la cual gracias al controller, recibo en la request los objetos para crear esta vista.

## Capturas de pantalla del proeycto

### Inicio (publica)
![screenshot](Capturas/Inicio.png)
### VerRutasNoLogin (pública)
![screenshot](Capturas/RutasNoLogin.png)
### Registro (pública)
![screenshot](Capturas/Registro.png)
### Login (pública)
![screenshot](Capturas/Login.png)

<hr><hr>

### Usuario Inicio (privada)
![screenshot](Capturas/Usuario.png)

### Usuario Ver Rutas (privada)
![screenshot](Capturas/UsuarioRutas.png)

### Usuario Hacer Valoración (privada)
![screenshot](Capturas/UsuarioValoracionHacer.png)

### Usuario Ver Valoración (privada)
![screenshot](Capturas/UsuarioValoracionVer.png)

### Usuario Ver Novedades (privada)
![screenshot](Capturas/Novedades.png)

### Usuario Ver Reservas (privada)
![screenshot](Capturas/UsuarioReservas.png)

<hr><hr>

### Admin Inicio (privada)
![screenshot](Capturas/Admin.png)

### Admin Crear Usuario (privada)
![screenshot](Capturas/AdminCrearUsuario.png)

### Admin Ver Usuarios (privada)
![screenshot](Capturas/AdminVerUsuarios.png)

### Admin Modificar Usuario (privada)
![screenshot](Capturas/AdminModificarUsuario.png)

### Admin Añadir Ruta (privada)
![screenshot](Capturas/AdminAñadirRuta.png)
