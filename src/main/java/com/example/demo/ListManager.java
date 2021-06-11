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

@WebServlet(name = "List", value = "/listf")
public class ListManager extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String side = SideController.getSide(request);
        request.setAttribute("caller","listf");
        request.setAttribute("side",side);
        if(request.getAttribute("ready") == null) {
            List<String> attributes = new ArrayList<>();
            attributes.add("list");
            request.setAttribute("attributes", attributes);
            request.getRequestDispatcher("JPA").forward(request, response);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/listField.jsp");
        rd.forward(request,response);
    }



    public void destroy() {
    }
}