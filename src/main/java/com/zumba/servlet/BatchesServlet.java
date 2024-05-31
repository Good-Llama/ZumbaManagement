package com.zumba.servlet;

import com.zumba.model.Batch;
import com.zumba.util.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/batches")
public class BatchesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // Add this line

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String timing = request.getParameter("timing");

        Batch batch = new Batch();
        batch.setName(name);
        batch.setTiming(timing);

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Batches (batch_name, timing) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, batch.getName());
                stmt.setString(2, batch.getTiming());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests
    }
}
