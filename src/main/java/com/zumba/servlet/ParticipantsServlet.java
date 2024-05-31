package com.zumba.servlet;

import com.zumba.model.Participant;
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

@WebServlet("/participants")
public class ParticipantsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // Add this line

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        Participant participant = new Participant();
        participant.setName(name);
        participant.setEmail(email);
        participant.setBatchId(batchId);

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Participants (name, email, batch_id) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, participant.getName());
                stmt.setString(2, participant.getEmail());
                stmt.setInt(3, participant.getBatchId());
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
