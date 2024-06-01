package dao;

import model.Participant;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {
    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT * FROM participants";
        try (Connection connection = DBConnectionUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Participant participant = new Participant();
                participant.setParticipantId(resultSet.getInt("participant_id"));
                participant.setName(resultSet.getString("name"));
                participant.setEmail(resultSet.getString("email"));
                participant.setBatchId(resultSet.getInt("batch_id"));
                participants.add(participant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    public void saveParticipant(Participant participant) {
        String query = "INSERT INTO participants (name, email, batch_id) VALUES (?, ?, ?)";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, participant.getName());
            preparedStatement.setString(2, participant.getEmail());
            preparedStatement.setInt(3, participant.getBatchId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement update and delete methods similarly
}
