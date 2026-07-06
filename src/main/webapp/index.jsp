<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .form-container { width: 450px; margin-bottom: 30px; }
        input[type="text"], input[type="email"], input[type="tel"] {
            width: 100%; padding: 6px; box-sizing: border-box; margin-bottom: 10px;
        }
        .btn-submit {
            padding: 8px 15px; background-color: #007bff; color: white; border: none; cursor: pointer;
        }
    </style>
</head>
<body>
<h2>Registrar Contacto</h2>
<div class="form-container">
    <form action="contacto" method="post">
        <label>Nombre(s):</label>
        <input type="text" name="nombre" required>

        <label>Apellido Paterno:</label>
        <input type="text" name="apellidoPaterno" required>

        <label>Apellido Materno:</label>
        <input type="text" name="apellidoMaterno" required>

        <label>Teléfono:</label>
        <input type="tel" name="telefono" required>

        <label>Teléfono Alternativo:</label>
        <input type="tel" name="telefonoAlternativo">

        <label>Correo Electrónico:</label>
        <input type="email" name="correo" required>

        <label>Red Social:</label>
        <input type="text" name="redSocial">

        <button type="submit" class="btn-submit">Registrar</button>
    </form>
</div>

<hr>

<h2>Lista de Contactos</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Teléfono</th>
        <th>Tel. Alternativo</th>
        <th>Correo</th>
        <th>Red Social</th>
    </tr>
    </thead>
    <tbody>

    <!--Parte del codigo que sirve para recorrer la lista de los contactos e ir rellenando cada
    parte de la tabla usando un forEach
    -->
    <c:forEach var="contacto" items="${listaContactos}">
        <tr>
            <td>${contacto.idContacto}</td>
            <td>${contacto.nombre}</td>
            <td>${contacto.apellidoPaterno} ${contacto.apellidoMaterno}</td>
            <td>${contacto.telefono}</td>
            <td>${contacto.telefonoAlternativo}</td>
            <td>${contacto.correo}</td>
            <td>${contacto.redSocial}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>