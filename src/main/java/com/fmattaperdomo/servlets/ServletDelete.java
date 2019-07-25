package com.fmattaperdomo.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fmattaperdomo.models.Product;
import com.fmattaperdomo.utilities.Utility;

@WebServlet(name = "ServletDelete", urlPatterns = {"/eliminar"})
public class ServletDelete extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        
        String idProducto = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(idProducto);
        } catch (NumberFormatException nfe) {
            id = 0;
        }
        
        List<Product> shoppingCart = (List) sesion.getAttribute("shoppingCart");
        if (shoppingCart != null) {
            for (Product product : shoppingCart) {
                if (product.getId_product() == id) {
                    shoppingCart.remove(product);
                    break;
                }
            }
        }
        
        sesion.setAttribute("shoppingCart", shoppingCart);
        Utility.getInstanceDB().gotoPage(request, response, getServletContext(), "/shoppingCart.jsp");
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
