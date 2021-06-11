package com.example.demo;

import com.example.demo.paradise.app.model.Bean;
import com.example.demo.paradise.app.model.Place;
import com.example.demo.paradise.app.model.Trip;

import java.io.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "CRUD", value = "/JPA")
public class CrudServlet extends HttpServlet {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ParadisePersist");
    private static EntityManager em;
    private static ServletContext app;

    public void init() {
        em = emf.createEntityManager();
        app = getServletContext();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getAttribute("attributes") != null){
            addAttributes((List<String>) request.getAttribute("attributes"),request);
            request.setAttribute("ready" , "ok");
            request.getRequestDispatcher(request.getAttribute("caller").toString()).forward(request,response);
        } else {
            EntityTransaction et = em.getTransaction();
            try{
                et.begin();
                doCrud(request,request.getParameter("action"));
                et.commit();
            } catch (Exception e){
                if(et.isActive()){
                    et.rollback();
                }
            }
        }
    }

    private static void manageRequest(HttpServletRequest request, String action, String object){
         if ("find".equals(action)) {
            if (request.getAttribute("side").equals("Trip")) {
                request.setAttribute(object, find(Trip.class, request.getParameter("id")));
            } else {
                request.setAttribute(object, find(Place.class, request.getParameter("id")));
            }
        }else if ("all".equals(action)) {
            if (object.equals("listP")) {
                if(app.getAttribute("appListP") == null)app.setAttribute("appListP", findAll("Place"));
                request.setAttribute("listP" , app.getAttribute("appListP"));
            }
            else {
                if(app.getAttribute("appList") == null)app.setAttribute("appList", findAll("Trip"));
                if(request.getAttribute("side").equals("Trip"))request.setAttribute("list", app.getAttribute("appList"));
                else request.setAttribute("list", app.getAttribute("appListP"));
            }
        }
    }

    public static void addAttributes(List<String> attributes, HttpServletRequest request) {
        String action = "";
        for(String attribute : attributes){
            switch (attribute) {
                case "listP", "list" -> action = "all";
                case "bean" -> action = "find";
            }
            manageRequest(request,action,attribute);
        }
    }

    public void destroy() {
        em.close();
    }

    private static void doCrud(HttpServletRequest request, String action){
        if ("pers".equals(action)) {
            persist(getBean(request));
        } else if ("mere".equals(action)) {
            merge(new Place(Long.valueOf(request.getParameter("id")),request.getParameter("name")));
        } else if ("remv".equals(action)) {
            if (request.getParameter("side").equals("Trip"))remove(find(Trip.class,request.getParameter("id")));
            else remove(find(Place.class,request.getParameter("id")));
        }
        app.setAttribute("appListP", findAll("Place"));
        app.setAttribute("appList", findAll("Trip"));
    }

    private static Bean getBean(HttpServletRequest request){
        try {
            if(request.getParameter("nameTrip") != null)
            return new Trip(null,request.getParameter("nameTrip"),
                    getPlace(Integer.parseInt(request.getParameter("depa"))),
                    getPlace(Integer.parseInt(request.getParameter("term"))),
                    Float.parseFloat(request.getParameter("cost")));
            else
                return new Place(null,request.getParameter("name"));
        } catch (Exception e){
            return null;
        }
    }

    private static Place getPlace(int id){
        List<Place> lp = (List<Place>) app.getAttribute("appListP");
        for (Place p : lp){
            if(p.getId() == id)return p;
        }
        return null;
    }

    private static void persist(Bean entity) {
        em.persist(entity);
    }

    private static Object find(Class<?> entityClass, String primaryKey) {
        return em.find(entityClass,Long.valueOf(primaryKey));
    }

    private static void merge(Bean entity) {
        em.merge(entity);
    }

    private static void remove(Object entity) {
        em.remove(entity);
    }

    private static List<Object> findAll(String side){
            return em.createQuery("from "+side).getResultList();
    }
}