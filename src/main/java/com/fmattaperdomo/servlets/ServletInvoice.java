package com.fmattaperdomo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fmattaperdomo.dao.PSQLInvoice;
import com.fmattaperdomo.models.Product;
import com.fmattaperdomo.models.Invoice_Header;
import com.fmattaperdomo.utilities.Utility;

@WebServlet(name = "ServletInvoice", urlPatterns = {"/vender"})
public class ServletInvoice extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
        String full_name = request.getParameter("full_name");
        String address_main = request.getParameter("address_main");
        String phone_main = request.getParameter("phone_main");
        
        List<Product> shoppingCart = (List) sesion.getAttribute("shoppingCart");
        
        PSQLInvoice dao = new PSQLInvoice();
        Invoice_Header invoice = dao.insertInvoice(full_name, address_main, phone_main, shoppingCart);
        sesion.removeAttribute("shoppingCart");
        request.setAttribute("invoice", invoice);
        Utility.getInstanceDB().gotoPage(request, response, getServletContext(), "/invoice.jsp");
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
