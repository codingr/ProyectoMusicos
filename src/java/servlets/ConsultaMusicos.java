/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Caracteristica;
import beans.Instrumento;
import beans.Musico;
import beans.Usuario;
import ejb.InstrumentosFachada;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "ConsultaMusicos", urlPatterns = {"/ConsultaMusicos"})
public class ConsultaMusicos extends HttpServlet {

    public static final String ID_INSTRUMENTO = "idinstrumento";
    public static final String INSTRUMENTOS = "instrumentos";

    public static final String MUSICOS = "musicos";
    public static final String INSTRUMENTO = "instrumento";
    public static final String ID_MUSICO = "idmusico";
    /**/
    public static final String ACCION = "accion";
    public static final String NOMBRE = "nombre";
    public static final String MUSICO = "musico";
    /*Páginas a las que nos podemos mover*/
    public static final String RUTA_PROYECTO_ASP
            = "ProyectoMusicosASP/Principal.aspx";
    public static final String RUTA_VER_MUSICO = "vermusico.jsp";
    public static final String RUTA_INICIO = "index.jsp";
    public static final String RUTA_VER_INSTRUMENTO = "verinstrumento.jsp";
    public static final String RUTA_REGISTRO = "registro.jsp";
    /*Las rutas deben ser relativas*/
    public static final String URLFOTOMUSICO = "images/Musicos";
    public static final String URLFOTOINSTRUMENTO = "images/Instrumentos";
    /*Posibles valores de la acción por la cual hemos llegado al servlet*/
    public static final String ACCION_VER_MUSICO = "vermusico";
    public static final String ACCION_LOGIN = "login";
    public static final String ACCION_ADMINISTRAR = "administrar";
    public static final String ACCION_VER_INSTRUMENTO = "verinstrumento";
    public static final String ACCION_LISTAR_MUSICOS = "listarmusicos";
    public static final String ACCION_DESCONECTAR = "desconectar";
    public static final String ACCION_LISTAR_INSTRUMENTOS
                                                    = "listarinstrumentos";
    public static final String ACCION_VER_CARACTERISTICAS_INSTRUMENTO
                                             = "vercaracteristicasinstrumento";
    public static final String ACCION_REGISTRO = "registro";
    /*Parámetros o atributos recibidos por servlets desde otra parte del 
     programa*/
    public static final String USUARIO = "usuario";
    public static final String ERROR_NOMBRE_VACIO
            = "Debes introducir un nombre";
    public static final String ERROR_NOMBRE_INVALIDO
            = "El usuario no se encuentra en la base de datos";
    public static final String ERROR_PASSWORD_VACIO
            = "Debes introducir el password";
    public static final String ERROR_PASSWORD_INCORRECTO
            = "Password incorrecto";
    public static final String PASSWORD = "password";
    public static final String ERRORES = "errores";

    @EJB
    private InstrumentosFachada instrumentosFachada;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accion = request.getParameter(ACCION);
        String destino;
        if (accion == null) {
            destino = RUTA_INICIO;
            request.getRequestDispatcher(destino).forward(request, response);
        } else {
            String letra;
            Instrumento instrumento;
            String strid;
            int id;
            switch (accion) {
                case ACCION_ADMINISTRAR:
                    destino = RUTA_PROYECTO_ASP;
                    request.getRequestDispatcher(destino).forward(request, response);
                    break;
                case ACCION_DESCONECTAR:
                    HttpSession sesion = request.getSession();
                    Usuario usuario = (Usuario) sesion.getAttribute(USUARIO);
                    usuario.setFechaultimaconexion(new Date());
                    instrumentosFachada.actualizarFechaUltimaConexionUsuario(usuario);
                    sesion.invalidate();
                    destino = RUTA_INICIO;
                    request.getRequestDispatcher(destino).forward(request, response);
                    break;
                case ACCION_LISTAR_INSTRUMENTOS:
                    letra = request.getParameter(INSTRUMENTOS);
                    List<Instrumento> instrumentos = instrumentosFachada.getInstrumentos(letra);
                    cargarXMLInstrumentos(instrumentos, out);
                    break;
                case ACCION_LISTAR_MUSICOS:
                    letra = request.getParameter(MUSICOS);
                    cargarXMLMusicos(letra, out);
                    break;
                case ACCION_LOGIN:
                    validarUsuario(request);
                    destino = RUTA_INICIO;
                    request.getRequestDispatcher(destino).forward(request, response);
                    break;
                case ACCION_VER_CARACTERISTICAS_INSTRUMENTO:
                    instrumento = (Instrumento) request.getSession().getAttribute(INSTRUMENTO);
                    cargarXMLCaracteristicasInstrumentos(instrumento, out);
                    break;
                case ACCION_VER_INSTRUMENTO:
                    strid = request.getParameter(ID_INSTRUMENTO);
                    id = Integer.parseInt(strid);
                    destino = RUTA_VER_INSTRUMENTO;
                    instrumento = instrumentosFachada.buscarInstrumento(id);
                    request.getSession().setAttribute(INSTRUMENTO, instrumento);
                    request.setAttribute("URLFOTOINSTRUMENTO", URLFOTOINSTRUMENTO);
                    request.getRequestDispatcher(destino).forward(request, response);
                    break;
                case ACCION_VER_MUSICO:
                    strid = request.getParameter(ID_MUSICO);
                    id = Integer.parseInt(strid);
                    destino = RUTA_VER_MUSICO;
                    request.setAttribute(MUSICO, instrumentosFachada.buscarMusico(id));
                    request.setAttribute("URLFOTOMUSICO", URLFOTOMUSICO);
                    request.getRequestDispatcher(destino).forward(request, response);
                    break;
                case ACCION_REGISTRO:
                    destino = RUTA_REGISTRO;
                    request.getRequestDispatcher(destino).forward(request, response);
                    break;
                default:
                    break;
            }
        }

    }

    private void validarUsuario(HttpServletRequest request) {
        ArrayList<String> errores = new ArrayList<>();
        String nombre = request.getParameter(NOMBRE);
        if (nombre == null || nombre.equals("")) {
            errores.add(ERROR_NOMBRE_VACIO);
        } else {
            Usuario usuario = instrumentosFachada.buscarUsuario(nombre);
            if (usuario == null) {
                errores.add(ERROR_NOMBRE_INVALIDO);
            } else {
                String password = request.getParameter(PASSWORD);
                if (password == null || password.equals("")) {
                    errores.add(ERROR_PASSWORD_VACIO);
                } else if (usuario.getPassword().equals(password)) {
                    request.getSession().setAttribute(USUARIO, usuario);
                } else {
                    errores.add(ERROR_PASSWORD_INCORRECTO);
                }
            }
        }
        request.setAttribute(ERRORES, errores);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void cargarXMLMusicos(String letra, PrintWriter out) {
        out.print("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        out.println("<musicos>");
        List<Musico> musicos = instrumentosFachada.getMusicos(letra);

        for (int i = 0; i < musicos.size(); i++) {
            out.println("<musico id='" + musicos.get(i).getIdE()
                    + "'>");
            out.println("<nombre>" + musicos.get(i).getNombre() + "</nombre>");
            out.println("<apellido>" + musicos.get(i).getApellido() + "</apellido>");
            out.println("<alias>" + musicos.get(i).getAlias() + "</alias>");
            out.println("</musico>");
        }

        out.println("</musicos>");
    }

    private void cargarXMLInstrumentos(List<Instrumento> instrumentos, PrintWriter out) {
        out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.println("<instrumentos>");
        for (int i = 0; i < instrumentos.size(); i++) {
            out.println("<instrumento id='" + instrumentos.get(i).getIdE()
                    + "'>");
            out.println("<marca>" + instrumentos.get(i).getMarca() + "</marca>");
            out.println("<modelo>" + instrumentos.get(i).getModelo() + "</modelo>");
            out.println("</instrumento>");
        }
        out.println("</instrumentos>");
    }

    private void cargarXMLCaracteristicasInstrumentos(Instrumento instrumento, PrintWriter out) {
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        List<Caracteristica> caracteristicas = instrumento.getCaracteristicaList();
        if (!caracteristicas.isEmpty()) {
            out.println("<caracteristicas>");
            for (int j = 0; j < caracteristicas.size(); j++) {
                out.println("<caracteristica>");
                String texto = caracteristicas.get(j).getTexto();
                int separador = texto.indexOf(":");
                out.println("<nombre>" + texto.substring(0, separador) + "</nombre>");
                out.println("<valor>" + texto.substring(separador + 1) + "</valor>");
                out.println("</caracteristica>");
            }
            out.println("</caracteristicas>");
        }
    }
}
