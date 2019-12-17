package com.mstrava.tools;

import com.mstrava.tools.db.DatabaseHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddRunServlet", urlPatterns = {"/addRun"})
public class AddRunServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int distance = Integer.parseInt(request.getParameter("distance"));
        int calories = Integer.parseInt(request.getParameter("calories"));;
        Timestamp start = Timestamp.valueOf(request.getParameter("start"));
        Timestamp end =  Timestamp.valueOf(request.getParameter("end"));

        try {
            DatabaseHelper.sendRunUpdate(username, distance, calories, start, end);
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Run successfully added");
        out.flush();
    }
}
