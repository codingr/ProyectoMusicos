<%-- 
    Document   : index
    Created on : 03-mar-2014, 23:59:19
    Author     : User
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            #mnu
            {
                float: left;
                width: 16%;
            }
            #letras{
                float: left;
                border-style: ridge;
                border-width: 10px;
            }
            #letras ul{
                list-style-type: none;
                margin: 0px;
                padding:0px;
                border-left-width: 1px;
                border-left-style: solid;
                border-left-color: #CCC;
            }
            #letras li{
                display: inline;
                font-family: Arial,Helvetica,sans-serif;
                color:#000;
                text-decoration: underline;
                font-size: 0.9em;
                display: block;
                padding: 6px;
                float: left;
                border-bottom-width: 1px;
                border-bottom-style: solid;
                border-bottom-color: #CCC;
                background-color: #EAEAEA;
                border-top-width: 1px;
                border-top-style: solid;
                border-top-color: #CCC;
                border-right-width: 1px;
                border-right-style: solid;
                border-right-color: #CCC;
            }
            #letras li:hover{
                color: #FFF;
                background-color: #666;
            }
            #mnu ul{
                list-style-type: none;
                margin: 0px;
                padding: 0px;
                border-top-width: 1px;
                border-right-width: 1px;
                border-left-width: 1px;
                border-top-style: solid;
                border-right-style: solid;
                border-left-style: solid;
                border-top-color: #CCC;
                border-right-color: #CCC;
                border-left-color: #CCC;
            }
            #mnu li{
                font-family: Arial,Helvetica,sans-serif;
                color:#000;
                text-decoration: none;
                font-size: 0.9em;
                display: block;
                padding: 6px;
                border-bottom-width: 1px;
                border-bottom-style: solid;
                border-bottom-color: #CCC;
                background-color: #EAEAEA;
            }
            #mnu li:hover{
                color: #FFF;
                background-color: #666;
                border-right-width: 5px;
                border-right-style: solid;
                border-right-color: #36F;
                cursor: url(images/Little_music_note.jpg),crosshair;
            }            
            .letraalfabeto{
                font-family: fantasy;
                margin: 5px;
            }
            .letraalfabeto:hover{
                font-weight: 900;
                font-size: larger;                
                cursor: url(images/Little_music_note.jpg),crosshair;
            }
            #lista{
                margin-left: 10px;
                clear: both;
            }
            #lista ul{
                list-style-type: square;
            }
        </style>
        <script type="text/javascript">
            var BUSCARMUSICO = 1;
            var BUSCARINSTRUMENTO = 0;
            var buscar;
            //Cargar las letras en las que pulsar para ver la lista de músicos
            //que empiezan por ese nombre
            function cargarAlfabeto() {
                if (document.getElementById("letras") == null)
                {
                    var letras = document.createElement("DIV");
                    var abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
                    var ul = document.createElement("UL");

                    for (var i = 0; i < 27; i++) {
                        var letra = document.createElement("LI");
                        var letraactual = abecedario.substr(i, 1);
                        //TRAS PULSAR EN UNA OPCIÓN DISTINTA
                        //DEL MENÚ SE MANTIENE EL VALOR DE ELEMENTOQUELLAMA
                        letra.onclick = function()
                        {
                            if (buscar == BUSCARMUSICO) {
                                cargarXMLMusicos(this);
                            } else {
                                cargarXMLInstrumentos(this);
                            }
                        };
                        letra.textContent = letraactual;
                        letra.id = "enlace" + letraactual;
                        letra.className = "letraalfabeto";
                        ul.appendChild(letra);
                    }
                    var todos = document.createElement("LI");
                    todos.textContent = "TODOS";
                    todos.id = "enlaceTodos";
                    todos.className = "letraalfabeto";
                    todos.onclick = function()
                    {
                        if (buscar == BUSCARMUSICO) {
                            cargarXMLMusicos(this);
                        } else {
                            cargarXMLInstrumentos(this);
                        }
                    };
                    ul.appendChild(todos);
                    letras.appendChild(ul);
                    letras.id = "letras";
                    //letras.elementoquellama=evt.currentTarget;
                    letras.style.visibility = "hidden";
                    document.body.appendChild(letras);

                }
            }
            function cargarXMLInstrumentos(elemento) {
                var xmlreq = new XMLHttpRequest();
                xmlreq.onreadystatechange = function() {
                    if (xmlreq.readyState == 4 && xmlreq.status == 200) {
                        procesarXMLInstrumentos(xmlreq);
                    }
                };
                if (elemento.textContent != "TODOS") {
                    xmlreq.open("GET", "ConsultaMusicos?accion=listarinstrumentos&instrumentos=" + elemento.textContent, true);
                } else {
                    xmlreq.open("GET", "ConsultaMusicos?accion=listarinstrumentos", true);
                }
                xmlreq.send();
            }
            function procesarXMLInstrumentos(xmlr) {
                var xml = xmlr.responseXML;
                var instrumentos = xml.getElementsByTagName("instrumento");
                var lista = document.getElementById("lista");
                lista.innerHTML = "";
                if (instrumentos.length > 0) {
                    var ul = document.createElement("UL");
                    for (var i = 0; i < instrumentos.length; i++) {
                        var li = document.createElement("LI");
                        var a = document.createElement("A");
                        var idinstrumento = instrumentos[i].getAttribute("id");//CREO QUE MAL, CORREGIR
                        a.href = "ConsultaMusicos?accion=verinstrumento&idinstrumento=" + idinstrumento;
                        a.textContent = instrumentos[i].textContent;
                        li.appendChild(a);
                        ul.appendChild(li);
                    }
                    lista.appendChild(ul);
                } else {
                    var h3 = document.createElement("H3");
                    h3.textContent = "No se han encontrado instrumentos cuya marca empiece por esa letra";
                    lista.appendChild(h3);
                }
            }
            function cargarXMLMusicos(elemento) {
                var xmlreq = new XMLHttpRequest();
                xmlreq.onreadystatechange = function() {
                    if (xmlreq.readyState == 4 && xmlreq.status == 200) {
                        procesarXMLMusicos(xmlreq);
                    }
                };
                if (elemento.textContent != "TODOS") {
                    xmlreq.open("GET", "ConsultaMusicos?accion=listarmusicos&musicos=" + elemento.textContent, true);
                } else {
                    xmlreq.open("GET", "ConsultaMusicos?accion=listarmusicos", true);
                }
                xmlreq.send();
            }
            function procesarXMLMusicos(xmlr) {
                var xml = xmlr.responseXML;
                var musicos = xml.getElementsByTagName("musico");
                var lista = document.getElementById("lista");
                lista.innerHTML = "";
                if (musicos.length > 0) {
                    var ul = document.createElement("UL");
                    for (var i = 0; i < musicos.length; i++) {
                        var li = document.createElement("LI");
                        var a = document.createElement("A");
                        var idmusico = musicos[i].getAttribute("id");
                        a.href = "ConsultaMusicos?accion=vermusico&idmusico=" + idmusico;
                        a.textContent = musicos[i].getElementsByTagName("nombre")[0].textContent;
                        li.appendChild(a);
                        ul.appendChild(li);
                    }
                    lista.appendChild(ul);
                } else {
                    var h3 = document.createElement("H3");
                    h3.textContent = "No se han encontrado músicos cuyo nombre empiece por esa letra";
                    lista.appendChild(h3);
                }
            }
            function inicializar() {
                cargarAlfabeto();
                var lista = document.createElement("DIV");
                lista.id = "lista";
                document.body.appendChild(lista);
                var mnu = document.getElementById("mnu");
                var posX = mnu.clientLeft + mnu.clientWidth;
                lista.style.left = posX + "px";

                document.getElementById("buscarmusico").onclick =
                        function() {
                            buscar = BUSCARMUSICO;
                            limpiarLista();
                            mostrarLetras();
                        };
                document.getElementById("buscarinstrumento").onclick =
                        function() {
                            buscar = BUSCARINSTRUMENTO;
                            limpiarLista();
                            mostrarLetras();
                        };
                document.getElementById("login").onclick =
                        function() {
                            //FALTA COMPROBAR SI ESTÁ LOGUEADO   
                            //
                            location.href = "d:/ProyectoMusicosASP/principal.aspx";//PREGUNTAR A MARTA
                            //{//CAMBIAR LA CONDICIÓN CUANDO LA SEPA
                            //setTimeout(irLogin(), 20000);

                        };
            }
            function mostrarLetras() {
                if (document.getElementById("letras")) {
                    var letras = document.getElementById("letras");
                    letras.style.visibility = "visible";
                }
            }

            function limpiarLista() {
                var lista = document.getElementById("lista");
                lista.innerHTML = "";
            }
            function irLogin()
            {
            
                /*if (comprobarLogin()) {
                    location.href = "d:\ProyectoMusicosASP\principal.aspx";//PREGUNTAR A MARTA
                }*/

            }
            function hacerLogin() {
                /*var login=document.getElementById("login");
                login
                var xmlreq = new XMLHttpRequest();
                xmlreq.onreadystatechange = function() {
                    if (xmlreq.readyState == 4 && xmlreq.status == 200) {
                        procesarXMLMusicos(xmlreq);
                    }
                };

                xmlreq.open("GET", "ConsultaMusicos?accion=login&musicos=" + elemento.textContent, true);
                xmlreq.send();*/
            }
        </script>
    </head>

    <body onload="inicializar()">
        <div id="mnu">
            <form action="ConsultaMusicos">
            <ul>
                <li id="buscarmusico">Buscar por músico</li>
                <li id="buscarinstrumento">Buscar por instrumento</li>
                <c:if test="${empty usuario}">
                    <li id="login">
                        Nombre:<input type="text" name="nombre" value="" />
                        Password:<input type="password" name="password" value="" />
                        <input type="submit" value="Login" name="accion" />
                    </li>
                </c:if>
            </ul>
            </form>
            <c:if test="${!empty usuario}">
                    ${usuario.nombre}                        
            </c:if>                    
        </div>
    </body>
</html>
