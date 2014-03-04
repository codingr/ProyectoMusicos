<%-- 
    Document   : index
    Created on : 03-mar-2014, 23:59:19
    Author     : User
--%>

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
            #letras{
                border-style: ridge;
                border-width: 10px;
            }
            
            .letramusico{
                font-family: fantasy;
                margin: 5px;
            }
            .letramusico:hover{
                font-weight: 900;
                font-size: larger;
                //text-decoration: underline;
                cursor: url(images/Little_music_note.jpg),crosshair;
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
            #lista ul{
                list-style-type: square;
            }
            
        </style>
        <script type="text/javascript">            
            //Cargar las letras en las que pulsar para ver la lista de músicos
            //que empiezan por ese nombre
            function cargarAlfabeto() {
                var letras = document.createElement("DIV");                
                var abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
                var ul=document.createElement("UL");                
                for (var i = 0; i < 27; i++) {
                    var letra = document.createElement("LI");
                    var letraactual = abecedario.substr(i, 1);
                    letra.onclick = function()
                    {
                        cargarXMLMusicos(this);
                    };
                    letra.textContent = letraactual;
                    letra.id = "enlace" + letraactual;
                    letra.className = "letramusico";
                    //MIRAR SI EL ONCLICK SE PUEDE ASIGNAR A LETRAS
                    //letra.onclick=function(){cargarXMLMusicos(letra.value);};
                    ul.appendChild(letra);

                }
                //var todos = document.createElement("LI");
                var todos = document.createElement("LI");
                todos.textContent = "TODOS";
                todos.id = "enlaceTodos";
                //.style.textDecoration = "underline";
                todos.className = "letramusico";
                todos.onclick = function()
                    {
                        cargarXMLMusicos(this);
                    };
                ul.appendChild(todos);
                letras.appendChild(ul);
                letras.id="letras";
                
                document.body.appendChild(letras);
            }
            function cargarXMLMusicos(elemento) {               
                var xmlreq=new XMLHttpRequest();
                xmlreq.onreadystatechange = function() {
                    if (xmlreq.readyState == 4 && xmlreq.status == 200) {
                        procesarXMLMusicos(xmlreq);
                    }
                };
                if (elemento.textContent!="TODOS"){
                    xmlreq.open("GET", "ConsultaMusicos?accion=listarmusicos&musicos=" + elemento.textContent, true);
                }else{
                    xmlreq.open("GET", "ConsultaMusicos?accion=listarmusicos", true);
                }
                xmlreq.send();
            }
            function procesarXMLMusicos(xmlr) {
                var xml = xmlr.responseXML;
                var musicos = xml.getElementsByTagName("musico");
                var lista = document.getElementById("lista");
                lista.innerHTML="";
                if (musicos.length > 0) {
                    var ul = document.createElement("UL");
                    for (var i = 0; i < musicos.length; i++) {
                        var li = document.createElement("LI");
                        var a=document.createElement("A");
                        var idmusico=musicos[i].getAttribute("id");
                        a.href="ConsultaMusicos?accion=vermusico&idmusico="+idmusico;
                        a.textContent=musicos[i].getElementsByTagName("nombre")[0].textContent;                        
                        
                        li.appendChild(a);
                        ul.appendChild(li);
                    }
                    lista.appendChild(ul);
                }else{
                    var h3=document.createElement("H3");
                    h3.textContent="No se han encontrado músicos cuyo nombre empiece por esa letra";
                    lista.appendChild(h3);
                }

            }
            function inicializar() {
                cargarAlfabeto();
            }
        </script>
        
        
    </head>
     
     <body onload="inicializar()">
        <div id="mnu">
            <ul id="cosilla">
                <li>Buscar por músico</li>
                <li>Buscar por instrumento</li>
                <li id="login">Hacer login</li>
            </ul>
        </div>
        
        <div id="lista"></div>
        
    </body>
</html>
