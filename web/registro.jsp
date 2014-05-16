<%-- 
    Document   : registro
    Created on : 25-mar-2014, 0:06:21
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            .error{
                color:red;
                visibility:hidden;
            } 
        </style>
        <script type="text/javascript">
            var botonPulsado=0;
            function validarUsuario() {
                var nombre = document.getElementsByName("nombre")[0];
                var error = document.getElementById("errornombre");
                if (nombre.value == "") {
                    mostrarError(error);
                    return false;
                } else {
                    ocultarError(error);
                    return true;
                }
            }
            function validarCorreo() {
                //var expreg = /^([a-zA-Z0-9])+\[@]([azA-Z0-9])+\[.]([azA-Z0-9])+$/;
                //var expreg = /\S+@\S+\.\S+/;
                var expreg= /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]+$/;
                var correo = document.getElementsByName("correo")[0];
                var error = document.getElementById("errorcorreo");
                if (!expreg.test(correo.value)) {
                    mostrarError(error);
                    return false;
                } else {
                    ocultarError(error);
                    return true;
                }
            }
            function validarPassword() {
                var password = document.getElementsByName("password")[0];
                var password2 = document.getElementsByName("password2")[0];
                var error = document.getElementById("errorpassword");
                if (password.value == "") {
                    mostrarError(error);
                    return false;
                } else {
                    ocultarError(error);
                }
                error = document.getElementById("errorpassword2");
                if (password.value != password2.value) {

                    mostrarError(error);
                    return false;
                } else {
                    ocultarError(error);
                    return true;
                }
            }
            function validarDatos() {
                //Comprobar si se puede hacer en un solo if
                var validar;
                validar = validarUsuario();
                if (validar) {
                    validar = validarPassword();
                    if (validar) {
                        validar = validarCorreo();
                        if (validar) {
                            
                            return true;
                        }
                    }
                }
                return false;
            }

            function comprobarBotonPulsado() {
                if (botonPulsado == 1) {
                    return validarDatos();
                } else {
                    //document.forms["frmGestionRegistros"].action = "ConsultaMusicos";
                    //document.forms["frmGestionRegistros"].submit();
                    return true;
                }

            }

            function inicializar() {

            }
            function mostrarError(error) {
                error.style.visibility = "visible";
            }
            function ocultarError(error) {
                error.style.visibility = "hidden";
            }
        </script>
    </head>
    <body onload="inicializar()">
        <h1>Gestión de registros</h1>
        <form id="frmGestionRegistros" action="GestionRegistros" onsubmit="return comprobarBotonPulsado()">
            <table>
                <tr>
                    <td>Nombre de usuario</td>
                    <td><input type="text" name="nombre"/></td>
                        <c:if test="${empty error_no_disponible}">
                        <td><span class="error" id="errornombre">Debes introducir un nombre</span></td>
                    </c:if>
                    <c:if test="${!empty error_no_disponible}">
                        <td><span class="error" id="errornombre">Usuario no disponible. Vuelve a intentarlo.</span></td>
                    </c:if>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"/></td>
                    <td><span class="error" id="errorpassword">Debes introducir una contraseña</span></td>
                </tr>
                <tr>
                    <td>Introduce de nuevo el password</td>
                    <td><input type="password" name="password2"/></td>
                    <td><span class="error" id="errorpassword2">Las contraseñas no coinciden. Prueba de nuevo.</span></td>
                </tr>
                <tr>
                    <td>Correo electrónico</td>
                    <td><input type="text" name="correo"/></td>
                    <td><span class="error" id="errorcorreo">Debes introducir un correo válido.</span></td>
                </tr>
            </table>
            <input type="submit" name="accion" value="Aceptar" onclick="botonPulsado = 1;"/>
            <input type="submit" name="accion" value="Cancelar" onclick="botonPulsado = 0;"/>
        </form>


    </body>
</html>
