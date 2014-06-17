/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Usuario;
import ejb.InstrumentosFachada;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "GestionRegistros", urlPatterns = {"/GestionRegistros"})
public class GestionRegistros extends HttpServlet {
    @EJB
    private InstrumentosFachada instrumentosFachada;
   
    public static final String ACCION_ACEPTAR = "Aceptar";
    public static final String ACCION_CANCELAR = "Cancelar";
    public static final String CORREO="correo";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter(ConsultaMusicos.ACCION);
        String destino = "";

        if (accion == null) {
            destino = ConsultaMusicos.RUTA_INICIO;
        } else if (accion.equalsIgnoreCase(GestionRegistros.ACCION_ACEPTAR)) {
            String nombre=request.getParameter(ConsultaMusicos.NOMBRE);
            String password=request.getParameter(ConsultaMusicos.PASSWORD);
            String correo=request.getParameter(GestionRegistros.CORREO);
            Usuario usuario=new Usuario();
           
            usuario.setAdministrador(false);
            usuario.setNombre(nombre);
            usuario.setPassword(password);
            usuario.setCorreo(correo);
            usuario.setFecharegistro(new java.util.Date());
            usuario.setFechaultimaconexion(new java.util.Date());            
            instrumentosFachada.añadirUsuario(usuario);
            request.getSession().setAttribute(ConsultaMusicos.USUARIO, usuario);
            destino = ConsultaMusicos.RUTA_INICIO;
        } else if (accion.equalsIgnoreCase(GestionRegistros.ACCION_CANCELAR)) {
            destino = ConsultaMusicos.RUTA_INICIO;
        }
        request.getRequestDispatcher(destino).forward(request, response);
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

}
