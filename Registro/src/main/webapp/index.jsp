<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Calificacion"%>
<%
    if (session.getAttribute("listacal") ==  null) {
            ArrayList<Calificacion> lisaux = new ArrayList<Calificacion>();
            session.setAttribute("listacal", lisaux);
        }
    ArrayList<Calificacion> lista =(ArrayList<Calificacion>)session.getAttribute("listacal");
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        
        
        <h1>Registro de Calificaciones</h1>
        <a href="MainServlet?op=nuevo" class="btnuevo">Nuevo registro</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>P1(30)</th>
                <th>P2(20)</th>
                <th>EF(40)</th> 
                <th>Nota</th> 
                <th></th>
                <th></th>
            </tr>
            <%
            if (lista !=null) {
                    for(Calificacion item:lista){
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getNombre() %></td> 
                <td><%= item.getP1() %></td> 
                <td><%= item.getP2() %></td> 
                <td><%= item.getEf() %></td>  
                <td><%= item.getP1()+item.getP2()+ item.getEf() %></td>
                <td>
                    <a href="MainServlet?op=editar&id=<%= item.getId() %>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%= item.getId() %>" onclick="return(confirm('Esta seguro de eliminar??'))"
                       >Eliminar</a>
                </td>
            <%
                    }
                }
            %>
            </tr>
        </table>
    </body>
</html>
