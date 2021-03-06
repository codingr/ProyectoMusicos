<%-- 
    Document   : vermusico
    Created on : 02-mar-2014, 20:52:51
    Author     : User
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de ver músico</title>
        <style type="text/css">
            img{
                float:right;
                max-height: 50%;                    
                max-width: 50%;
                    
            }
        </style>
    </head>
    <body>
        <h1>${musico.nombre} ${musico.apellido} <c:if test="${!empty musico.alias}">alias ${musico.alias}</c:if></h1>
        <table border="1">
            <tr>
                <td><strong>Fecha de nacimiento</strong></td>
                <td><fmt:formatDate value="${musico.fechanacimiento}" dateStyle="LONG"/></td>
            </tr>
            <c:if test="${!empty musico.fechadefuncion}">
            <tr>
                <td>Fecha de defunción</td>
                <td>${musico.fechadefuncion}</td>
            </tr>
            </c:if>
            <c:forEach items="${musico.instrumentoList}" var="instrumento">
                <c:set var="contador" value="${contador+1}"/>
            <tr>
                <td>Instrumento ${contador}</td>
                <td>
                    <a href="ConsultaMusicos?accion=verinstrumento&idinstrumento=${instrumento.idE}">${instrumento.marca} ${instrumento.modelo}</a>                    
                </td>
            </tr>
            </c:forEach>
            
        </table>
        <img src="${URLFOTOMUSICO}/${musico.urlfoto}" alt="Foto de músico" />
        <form action="ConsultaMusicos">
            <h3>Comentarios</h3>
            <c:if test="${!empty comentarios}">
                <c:forEach var="comentario" items="${comentarios}">
                    <b>${comentario.idUsuario.nombre}</b><br/>
                    ${comentario.mensaje}<br/>
                    <a href="ConsultaMusicos?accion=gusta&idComentario=${comentario.idcomentario}">Me gusta</a>
                    ${comentario.gusta} 
                    <a href="ConsultaMusicos?accion=nogusta&idComentario=${comentario.idcomentario}">No me gusta</a>
                    ${comentario.nogusta} 
                    <hr/>
                </c:forEach>
            </c:if>
            <c:if test="${!empty usuario}">
                <h2>Introduce tu comentario</h2>        
                ${usuario.nombre}<br/>
                Comentario<br/>
                <textarea name="mensaje" rows="4" cols="80"></textarea><br/>
                <input type="submit" value="Guardar comentario" name="accion" />
            </c:if>

            <input type="submit" value="Volver al inicio" name="inicio" />

        </form>
        
    </body>
</html>
