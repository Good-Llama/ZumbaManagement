package com.zumba.servlet;

import com.zumba.model.Participant;
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

@WebServlet("/participants")
public class ParticipantsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Participants (name, email, batch_id) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setInt(3, batchId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("participants.html"); // Redirect to form page after insertion
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Participant> participants = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Participants";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Participant participant = new Participant();
                    participant.setId(rs.getInt("participant_id"));
                    participant.setName(rs.getString("name"));
                    participant.setEmail(rs.getString("email"));
                    participant.setBatchId(rs.getInt("batch_id"));
                    participants.add(participant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("participants", participants);
        request.getRequestDispatcher("/listParticipants.jsp").forward(request, response);
    }
}
