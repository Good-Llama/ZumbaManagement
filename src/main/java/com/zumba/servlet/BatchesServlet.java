package com.zumba.servlet;

import com.zumba.model.Batch;
import com.zumba.util.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/batches")
public class BatchesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String timing = request.getParameter("timing");

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Batches (batch_name, timing) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, timing);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("batches.html"); // Redirect to form page after insertion
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Batch> batches = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Batches";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Batch batch = new Batch();
                    batch.setId(rs.getInt("batch_id"));
                    batch.setName(rs.getString("batch_name"));
                    batch.setTiming(rs.getString("timing"));
                    batches.add(batch);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
