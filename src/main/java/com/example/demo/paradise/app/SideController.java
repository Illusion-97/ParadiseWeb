package com.example.demo.paradise.app;

import javax.servlet.http.HttpServletRequest;

public class SideController {

    public static String getSide(HttpServletRequest request){
        String side = "Trip";
        if (request.getParameter("side") != null) side = request.getParameter("side");
        return side;
    }
}
