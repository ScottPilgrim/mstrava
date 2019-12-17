package com.mstrava.tools;

import com.mstrava.tools.db.DatabaseHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet(name = "GetStatsServlet", urlPatterns = {"/getStats"})
public class GetStatsServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Timestamp start = Timestamp.valueOf(request.getParameter("start"));
        Timestamp end =  Timestamp.valueOf(request.getParameter("end"));

        try {
            String resultJson = DatabaseHelper.sendGetStats(username, start, end);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(resultJson);
            out.flush();
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
    }
}
