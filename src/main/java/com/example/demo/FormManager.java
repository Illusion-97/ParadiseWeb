package com.example.demo;

import com.example.demo.paradise.app.SideController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Form", value = "/form")
public class FormManager extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd;
        if(request.getAttribute("ready") == null)setAttributes(request,response);
        rd = request.getRequestDispatcher("/WEB-INF/formField.jsp");
        rd.forward(request,response);
    }
    private void setAttributes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> attributes = new ArrayList<>();
        request.setAttribute("caller","form");
        String side = SideController.getSide(request);
        request.setAttribute("side",side);
        if(side.equals("Trip")){
            attributes.add("listP");
        }
        if (request.getParameter("id") != null) {
            attributes.add("bean");
        }
        request.setAttribute("attributes",attributes);
        request.getRequestDispatcher("JPA").forward(request,response);
    }

    public void destroy() {
    }
}