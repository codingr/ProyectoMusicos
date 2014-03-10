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
                <td>${instrumento.marca} ${instrumento.modelo}</td>
            </tr>
            </c:forEach>
            
        </table>
        <img src="${urlfotosmusicos}/${musico.urlfoto}" alt="Foto de músico" />
        <form action="ConsultaMusicos">
            <input type="submit" value="Volver al inicio" name="inicio" />
        </form>
        
    </body>
</html>
