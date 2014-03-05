<%-- 
    Document   : verinstrumento
    Created on : 05-mar-2014, 8:03:25
    Author     : alumno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de instrumento</title>
        <style type="text/css">
            img{
                float:right;
                max-height: 50%;                    
                max-width: 50%;

            }
        </style>
        <script type="text/javascript">



        </script>
    </head>
    <body>
        <h1>${instrumento.marca} ${instrumento.modelo}</h1>
        <h3>Año de fabricación:${instrumento.aniofabricacion}</h3>
        <img src="${urlfotosinstrumentos}/${instrumento.urlfoto}" alt="foto instrumento"/>
        <c:if test="${!empty instrumento.musicoList}">
            <h3>Músicos que usan este instrumento:</h3>
            <ul>
                <c:forEach items="${instrumento.musicoList}" var="musico">
                    <li><a href="ConsultaMusicos?accion=vermusico&idmusico=${musico.idmusico}">${musico.nombre} ${musico.apellido} ${musico.alias}</a></li>
                    </c:forEach>
            </ul>
        </c:if>  
        <form action="ConsultaMusicos">
            <input type="submit" value="Volver al inicio" name="inicio" />
        </form>
    </body>
</html>
