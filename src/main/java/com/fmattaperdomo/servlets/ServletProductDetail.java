package com.fmattaperdomo.servlets;

import com.fmattaperdomo.dao.PSQLCategory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fmattaperdomo.dao.PSQLProduct;
import com.fmattaperdomo.models.Category;
import com.fmattaperdomo.models.Product;
import com.fmattaperdomo.utilities.Utility;
import java.util.List;

@WebServlet(name = "ServletProductDetail", urlPatterns = {"/producto-detalle"})
public class ServletProductDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String sId = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(sId);
        } catch (NumberFormatException nfe) {
            id = 0;
        }
        
        PSQLProduct dao = new PSQLProduct();
        Product product = dao.getByID(id);
        
        PSQLCategory daoCategory = new PSQLCategory();
        List<Category> categories = daoCategory.getByALL();

        
        request.setAttribute("product", product);
        request.setAttribute("listCategories", categories);
        Utility.getInstanceDB().gotoPage(request, response, getServletContext(), "/product-detail.jsp");
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
