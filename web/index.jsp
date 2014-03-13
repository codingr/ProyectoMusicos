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
            #lista{
                margin-left: 10px;
                float: left;
                visibility: hidden;
            }
            #lista ul{
                list-style-type: square;
            }
            #letras{
                float: left;
                border-style: ridge;
                border-width: 10px;
                //display: block; //de prueba
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

            .letraalfabeto{
                font-family: fantasy;
                margin: 5px;
            }
            .letraalfabeto:hover{
                font-weight: 900;
                font-size: larger;                
                cursor: url(images/Little_music_note.jpg),crosshair;
            }

            #botonesdesplazamiento{
                visibility: hidden;
            }
            //PRUEBAS, BORRABLE
            .clasedatoslistados{
               top:160px;
               border-style: solid;
               border-width: 5px;
               //border: #36F;               
                //float: left;
               display: inline;//de prueba
                //height: 50%;
            }
            .clasedatoslistados ul{
                //float: left;
                //display: block;//de prueba
                //border: #36F;
                display: block;
            }
            .clasedatoslistados li{
                //float: left;
                list-style-type: circle;
                //display: block;//de prueba
            }

        </style>
        <script type="text/javascript">
            var ELEMENTOSPORPAGINA = 5;
            var BUSCARMUSICO = 1;
            var BUSCARINSTRUMENTO = 0;
            var instrumentosomusicos;
            var buscar;
            var paginaactual = 1;
            var totalpaginas;            
            var instrumentosomusicoslista;
            /*Cargar las letras en las que pulsar para ver la lista de músicos
            que empiezan por ese nombre*/
            function cargarAlfabeto() {
                var letras = document.getElementById("letras");
                var abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
                var ul = document.createElement("UL");
                for (var i = 0; i < 27; i++) {
                    var letra = document.createElement("LI");
                    var letraactual = abecedario.substr(i, 1);
                    letra.onclick = function()
                    {
                        if (buscar == BUSCARMUSICO) {
                            cargarXMLMusicos(this);
                        } else {
                            cargarXMLInstrumentos(this);
                        }
                    };
                    letra.textContent = letraactual;
                    //letra.id = "enlace" + letraactual;
                    letra.className = "letraalfabeto";
                    ul.appendChild(letra);
                }
                var todos = document.createElement("LI");
                todos.textContent = "TODOS";
                //todos.id = "enlaceTodos";
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
                var datoslistados = document.getElementById("datoslistados");
                limpiarDatosListados();
                if (instrumentos.length > 0) {
                    instrumentosomusicoslista=instrumentos;
                    rellenarLista();
                } else {
                    ocultarBotones();
                    var h3 = document.createElement("H3");
                    h3.textContent = "No se han encontrado instrumentos cuya marca empiece por esa letra";
                    datoslistados.appendChild(h3);
                }
            }
            /*Para mostrar en pantalla la lista de instrumentos o musicos*/
            function rellenarLista() {
                var datoslistados = document.getElementById("datoslistados");
                //datoslistados.className="datoslistados";
                var i;                
                limpiarDatosListados();        
                var ul = document.createElement("UL");
                var sobrantes = (instrumentosomusicoslista.length % ELEMENTOSPORPAGINA == 0) ? 0 : 1;
                var entero = Math.floor(instrumentosomusicoslista.length / ELEMENTOSPORPAGINA);
                totalpaginas = entero + sobrantes;                             
                
                if (buscar == BUSCARMUSICO) {
                    for (i = (paginaactual - 1) * ELEMENTOSPORPAGINA;
                            i < paginaactual * ELEMENTOSPORPAGINA
                            && i < instrumentosomusicoslista.length;
                            i++) {
                        var li = document.createElement("LI");
                        var a = document.createElement("A");
                        var idmusico = instrumentosomusicoslista[i].getAttribute("id");
                        a.href = "ConsultaMusicos?accion=vermusico&idmusico=" + idmusico;
                        a.textContent =
                                instrumentosomusicoslista[i].getElementsByTagName("nombre")[0].textContent
                                + " " + instrumentosomusicoslista[i].getElementsByTagName("apellido")[0].textContent;
                        li.appendChild(a);
                        ul.appendChild(li);
                    }
                } else if (buscar == BUSCARINSTRUMENTO) {
                    for (i = (paginaactual - 1) * ELEMENTOSPORPAGINA;
                            i < paginaactual * ELEMENTOSPORPAGINA
                            && i < instrumentosomusicoslista.length;
                            i++) {
                        var li = document.createElement("LI");
                        var a = document.createElement("A");
                        var idinstrumento = instrumentosomusicoslista[i].getAttribute("id");
                        a.href = "ConsultaMusicos?accion=verinstrumento&idinstrumento=" + idinstrumento;
                        a.textContent =
                                instrumentosomusicoslista[i].getElementsByTagName("marca")[0].textContent
                                + " " + instrumentosomusicoslista[i].getElementsByTagName("modelo")[0].textContent;
                        li.appendChild(a);
                        ul.appendChild(li);
                    }
                }
                habilitarBotones();
                
                datoslistados.appendChild(ul);
                
                datoslistados.style.visibility = "visible";
                datoslistados.className="clasedatoslistados";
                mostrarBotones();
            }
            /*Habilita o deshabilita botones en función de la página actual 
             * de la lista de instrumentos o músicos*/
            function habilitarBotones() {
                if (totalpaginas == 1) {
                    document.getElementById("btnPrimero").disabled = "true";
                    document.getElementById("btnAnterior").disabled = "true";
                    document.getElementById("btnSiguiente").disabled = "true";
                    document.getElementById("btnUltimo").disabled = "true";
                    //}
                } else if (paginaactual == 1) {
                    document.getElementById("btnPrimero").disabled = "true";
                    document.getElementById("btnAnterior").disabled = "true";
                    document.getElementById("btnSiguiente").removeAttribute("DISABLED");
                    document.getElementById("btnUltimo").removeAttribute("DISABLED");
                }
                else if (paginaactual == totalpaginas) {
                    document.getElementById("btnPrimero").removeAttribute("DISABLED");
                    document.getElementById("btnAnterior").removeAttribute("DISABLED");
                    document.getElementById("btnSiguiente").disabled = "true";
                    document.getElementById("btnUltimo").disabled = "true";
                } else {
                    document.getElementById("btnPrimero").removeAttribute("DISABLED");
                    document.getElementById("btnAnterior").removeAttribute("DISABLED");
                    document.getElementById("btnSiguiente").removeAttribute("DISABLED");
                    document.getElementById("btnUltimo").removeAttribute("DISABLED");
                }

            }
            function anterior() {
                paginaactual--;
                document.getElementById("btnSiguiente").removeAttribute("DISABLED");
                document.getElementById("btnUltimo").removeAttribute("DISABLED");
                if (paginaactual == 1) {
                    document.getElementById("btnPrimero").disabled = "true";
                    document.getElementById("btnAnterior").disabled = "true";
                } else {
                    document.getElementById("btnPrimero").removeAttribute("DISABLED");
                    document.getElementById("btnAnterior").removeAttribute("DISABLED");
                }
                rellenarLista();
            }
            function primero() {
                paginaactual = 1;
                document.getElementById("btnPrimero").disabled = "true";
                document.getElementById("btnAnterior").disabled = "true";
                if (totalpaginas == 1) {
                    document.getElementById("btnSiguiente").disabled = "true";
                    document.getElementById("btnUltimo").disabled = "true";
                } else {
                    document.getElementById("btnSiguiente").removeAttribute("DISABLED");
                    document.getElementById("btnUltimo").removeAttribute("DISABLED");
                }
                rellenarLista();
            }

            function siguiente() {
                paginaactual++;
                document.getElementById("btnAnterior").removeAttribute("DISABLED");
                document.getElementById("btnPrimero").removeAttribute("DISABLED");
                if (paginaactual == totalpaginas) {
                    document.getElementById("btnSiguiente").disabled = "true";
                    document.getElementById("btnUltimo").disabled = "true";
                } else {
                    document.getElementById("btnSiguiente").removeAttribute("DISABLED");
                    document.getElementById("btnUltimo").removeAttribute("DISABLED");
                }
                rellenarLista();
            }
            function ultimo() {
                paginaactual = totalpaginas;
                document.getElementById("btnAnterior").removeAttribute("DISABLED");
                document.getElementById("btnPrimero").removeAttribute("DISABLED");
                if (paginaactual == totalpaginas) {
                    document.getElementById("btnSiguiente").disabled = "true";
                    document.getElementById("btnUltimo").disabled = "true";
                } else {
                    document.getElementById("btnSiguiente").removeAttribute("DISABLED");
                    document.getElementById("btnUltimo").removeAttribute("DISABLED");
                }
                rellenarLista();
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
                var datoslistados = document.getElementById("datoslistados");
                limpiarDatosListados();
                if (musicos.length > 0) {
                    instrumentosomusicoslista=musicos;
                    rellenarLista();
                } else {
                    ocultarBotones();
                    var h3 = document.createElement("H3");
                    h3.textContent = "No se han encontrado músicos cuya nombre empiece por esa letra";
                    datoslistados.appendChild(h3);
                }
            }
            function inicializar() {
                cargarAlfabeto();
                var mnu = document.getElementById("mnu");
                var posX = mnu.clientLeft + mnu.clientWidth;
                var lista = document.getElementById("lista");
                lista.style.left = posX + "px";
                document.getElementById("buscarmusico").onclick =
                        function() {
                            buscar = BUSCARMUSICO;
                            limpiarDatosListados();
                            mostrarDivLista();
                            ocultarBotones();
                        };
                document.getElementById("buscarinstrumento").onclick =
                        function() {
                            buscar = BUSCARINSTRUMENTO;
                            limpiarDatosListados();
                            mostrarDivLista();
                            ocultarBotones();
                        };
            }
            function mostrarDivLista() {
                var lista = document.getElementById("lista");
                lista.style.visibility = "visible";
            }

            function limpiarDatosListados() {
                var datoslistados = document.getElementById("datoslistados");
               while (datoslistados.hasChildNodes()) {
                    datoslistados.removeChild(datoslistados.firstChild);
                }
            }
            function ocultarBotones() {
                var botonesDesplazamiento = document.getElementById("botonesdesplazamiento");
                botonesDesplazamiento.style.visibility = "hidden";
            }
            function mostrarBotones() {
                var botonesDesplazamiento = document.getElementById("botonesdesplazamiento");
                botonesDesplazamiento.style.visibility = "visible";
            }
            function hacerLogin() {
                /*if (document.getElementById("administracion")){
                 document.getElementById("administracion").onclick=
                 function (){
                 location.href="ProyectoMusicosASP/Principal.aspx?idusuario=1";
                 };
                 }*/
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

                <c:if test="${!empty usuario}">
                    ${usuario.nombre}
                    <a href="ConsultaMusicos?accion=desconectar">Desconectar</a>
                    <c:if test="${usuario.administrador}">
                        <div id="algo" onclick="hacerLogin()">
                            a href="ProyectoMusicosASP/Principal.aspx"  id="administracion">?idusuario=${usuario.idusuario} Administrar /a
                        </div>
                    </c:if>

                </c:if>  
            </form>
        </div>
        <div id="lista">
            <div id="letras"></div>
            <div id="datoslistados" class="clasedatoslistados">&nbsp;</div>
            <div id="botonesdesplazamiento">
                <input type="button" id="btnPrimero" value="Primero" onclick="primero()" />
                <input type="button" id="btnAnterior" value="Anterior" onclick="anterior()" />
                <input type="button" id="btnSiguiente" value="Siguiente" onclick="siguiente()" />
                <input type="button" id="btnUltimo" value="Último" onclick="ultimo()" />
            </div>
        </div>
        <c:if test="${!empty errores}">
            <div>
                <ul>
                    <c:forEach items="${errores}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </body>
</html>
