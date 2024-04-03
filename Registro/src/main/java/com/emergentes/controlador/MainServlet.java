package com.emergentes.controlador;

import com.emergentes.modelo.Calificacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        Calificacion cal = new Calificacion();
        int id, pos;

        HttpSession ses = request.getSession();
        ArrayList<Calificacion> lista = (ArrayList<Calificacion>) ses.getAttribute("listacal");

        switch (op) {
            case "nuevo":
                //enviar un objeto vacio a editar
                request.setAttribute("miobjcal", cal);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                //enviar un objeto a editar
                id = Integer.parseInt(request.getParameter("id"));
                //averiguar elemnto
                pos = buscarPorIndice(request, id);
                cal = lista.get(pos);
                request.setAttribute("miobjcal", cal);
                request.getRequestDispatcher("editar.jsp").forward(request, response);

                break;
            case "eliminar":
                //eliminae el registro segun id
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request, id);
                if (pos >= 0) {
                    lista.remove(pos);
                }
                request.setAttribute("listacal", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0; // Valor predeterminado
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        HttpSession ses = request.getSession();
        ArrayList<Calificacion> lista = (ArrayList<Calificacion>) ses.getAttribute("listacal");
        if (lista == null) {
            lista = new ArrayList<>();
        }
        
        Calificacion objcal = new Calificacion();
        objcal.setId(id);
        objcal.setNombre(request.getParameter("nombre"));
        objcal.setP1(Integer.parseInt(request.getParameter("p1")));
        objcal.setP2(Integer.parseInt(request.getParameter("p2")));
        objcal.setEf(Integer.parseInt(request.getParameter("ef")));
        
        if (id == 0) {
            // Nuevo registro
            int idNuevo = obtenerId(request);
            objcal.setId(idNuevo);
            lista.add(objcal);
        } else {
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objcal);
        }
        
        ses.setAttribute("listacal", lista);
        response.sendRedirect("index.jsp");
    }

    public int buscarPorIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        ArrayList<Calificacion> lista = (ArrayList<Calificacion>) ses.getAttribute("listacal");

        int pos = -1;
        if (lista != null) {
            for (Calificacion ele : lista) {
                ++pos;
                if (ele.getId() == id) {
                    break;
                }
            }
        }
        return pos;

    }

    public int obtenerId(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<Calificacion> lista = (ArrayList<Calificacion>) ses.getAttribute("listacal");

        int idn = 0;
        for (Calificacion ele : lista) {
            idn = ele.getId();
        }
        return idn + 1;
    }

}
