
<%@page import="com.emergentes.modelo.Calificacion"%>
<%
    Calificacion cal =(Calificacion) request.getAttribute("miobjcal");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro</h1>
        <form action="MainServlet" method="post">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="<%= cal.getId() %>" size="2" readonly></td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value="<%= cal.getNombre() %>"></td>
                    
                </tr>
                <tr>
                    <td>P1(30)</td>
                    <td><input type="text" name="p1" value="<%= cal.getP1() %>"></td>
                    
                </tr>
                <tr>
                    <td>P2(30)</td>
                    <td><input type="text" name="p2" value="<%= cal.getP2() %>"></td>
                    
                </tr>
                <tr>
                    <td>EF(40)</td>
                    <td><input type="text" name="ef" value="<%= cal.getEf() %>"></td>
                    
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"  value="ENVIAR"></td>
                    
                </tr>
            </table>
            
        </form>
    </body>
</html>
