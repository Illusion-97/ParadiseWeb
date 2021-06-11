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

@WebServlet(name = "Paradise", value = "/Paradise")
public class SunshineParadise extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getAttribute("ready") == null)setAttributes(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Paradise.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void setAttributes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> attributes = new ArrayList<>();
        request.setAttribute("caller","Paradise");
        String side = SideController.getSide(request);
        request.setAttribute("side",side);
        if(side.equals("Trip")){
            attributes.add("listP");
        }
        attributes.add("list");
        request.setAttribute("attributes",attributes);
        request.getRequestDispatcher("JPA").forward(request,response);
    }

    public void destroy() {
    }
}