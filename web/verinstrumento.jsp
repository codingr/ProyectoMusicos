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
            function cargarXMLCaracteristicasInstrumento() {
                var xmlreq = new XMLHttpRequest();
                xmlreq.onreadystatechange = function() {
                    if (xmlreq.readyState == 4 && xmlreq.status == 200) {
                        procesarXMLCaracteristicasInstrumento(xmlreq);

                    }
                };

                xmlreq.open("GET", "ConsultaMusicos?accion=vercaracteristicasinstrumento", true);
                xmlreq.send();
            }
            function procesarXMLCaracteristicasInstrumento(xmlr) {
                //alert("asld1");
                var xml = xmlr.responseXML;
                var caracteristicas = xml.getElementsByTagName("caracteristica");
                var ul = document.getElementById("caracteristicas");
                if (ul != null) {
                    //alert("asld2");
                    while (ul.hasChildNodes()) {
                        ul.removeChild(ul.firstChild);
                    }

                    for (var i = 0; i < caracteristicas.length; i++) {
                        var caracteristica = caracteristicas[i];
                        var li = document.createElement("LI");
                        var span1 = document.createElement("SPAN");
                        var span2 = document.createElement("SPAN");
                        var nombre = document.createTextNode(caracteristica.getElementsByTagName("nombre")[0].textContent);
                        var valor = document.createTextNode(caracteristica.getElementsByTagName("valor")[0].textContent);
                        span1.appendChild(nombre);
                        span1.style.backgroundColor = "green";
                        span2.appendChild(valor);
                        span2.style.backgroundColor = "red";
                        //span1.style.textDecoration="underline";
                        //span2.style.textDecoration="underline";                   
                        //alert("hola"+i)
                        li.appendChild(span1);
                        li.appendChild(span2);
                        ul.appendChild(li);
                    }
                }
            }
            function inicializarInstrumento() {
                cargarXMLCaracteristicasInstrumento();
            }

        </script>
    </head>
    <body onload="inicializarInstrumento()">
        <h1>${instrumento.marca} ${instrumento.modelo}</h1>

        <h3>Año de fabricación:${instrumento.aniofabricacion}</h3>
        <c:if test="${!empty instrumento.urlfoto}">
            <img src="${URLFOTOINSTRUMENTO}/${instrumento.urlfoto}" alt="foto instrumento" title="foto instrumento"/>
        </c:if>
        <c:if test="${!empty instrumento.musicoList}">
            <h3>Músicos que usan este instrumento:</h3>
            <ul>
                <c:forEach items="${instrumento.musicoList}" var="musico">
                    <li><a href=
                           "ConsultaMusicos?accion=vermusico&idmusico=${musico.idE}">${musico.nombre} ${musico.apellido} ${musico.alias}</a></li>
                    </c:forEach>
            </ul>
        </c:if>  
        <c:if test="${!empty instrumento.caracteristicaList}">
            <h3>Características:</h3>
            <ul id="caracteristicas">
                <!--
                  c:forEach items=" { instrumento.caracteristicaList}" var="caracteristica"
                 li> {caracteristica.texto}</li
                   c:forEach
                -->
            </ul>
        </c:if>      


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
