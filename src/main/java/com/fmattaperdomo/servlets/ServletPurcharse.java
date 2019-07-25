package com.fmattaperdomo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fmattaperdomo.dao.PSQLProduct;
import com.fmattaperdomo.dao.PSQLCategory;
import com.fmattaperdomo.models.Product;
import com.fmattaperdomo.models.Category;
import com.fmattaperdomo.utilities.Utility;

@WebServlet(name = "ServletPurchase", urlPatterns = {"/agregar-producto"})
public class ServletPurcharse extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
        
        String sId = request.getParameter("idproduct");
        String sCategory = request.getParameter("category");
        
        short idCategory;
        int   idProduct;
        
        try {
            idProduct = Integer.parseInt(sId);
            idCategory = Short.parseShort(sCategory);
        } catch (NumberFormatException nfe) {
            idProduct = 0;
            idCategory = 0;
        }
        
        PSQLProduct daoProduct = new PSQLProduct();
        PSQLCategory daoCategory = new PSQLCategory();
        
        Product product = daoProduct.getByID(idProduct);
        Category category = daoCategory.getByID(idCategory);
        product.setCategory(category);
        
        List<Product> shoppingCart = (List) sesion.getAttribute("shoppingCart");
        
        if (shoppingCart == null) {
            shoppingCart = new ArrayList<>();
        }
        
        shoppingCart.add(product);
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
