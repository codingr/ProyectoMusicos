/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.Musico;
import ejb.InstrumentosFachada;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import utils.Globales;

/**
 *
 * @author User
 */
@WebServlet(name = "ConsultaMusicos", urlPatterns = {"/ConsultaMusicos"})
public class ConsultaMusicos extends HttpServlet {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String accion=request.getParameter("accion");
        
        String destino="";
        if (accion==null){
            destino="index.jsp";
            request.getRequestDispatcher(destino).forward(request, response);
        }
        else if (accion.equalsIgnoreCase("listarmusicos")){
            String letra=request.getParameter("musicos");
            cargarXMLMusicos(letra,out);
            
        }else if (accion.equalsIgnoreCase("vermusico")){
            String strid=request.getParameter("idmusico");
            int id=Integer.parseInt(strid);
            destino="vermusico.jsp";
            request.setAttribute("musico", instrumentosFachada.buscarMusico(id));
            request.setAttribute("urlfotosmusicos", Globales.URLFOTOMUSICO); 
            request.getRequestDispatcher(destino).forward(request, response);
        }        
        
        //request.getRequestDispatcher(destino).forward(request, response);
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConsultaMusicos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsultaMusicos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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

    private void cargarXMLMusicos(String letra,PrintWriter out) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        out.print("<?xml version=\"1.0\" ?>");
        out.println("<musicos>");
        List<Musico> musicos = instrumentosFachada.getMusicos(letra);
        for (int i = 0; i < musicos.size(); i++) {
            out.println("<musico id='" + musicos.get(i).getIdmusico()
                    + "'>");
            out.println("<nombre>" + musicos.get(i).getNombre() + "</nombre>");
            out.println("</musico>");
        }

        out.println("</musicos>");
    }

}
