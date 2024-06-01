package dao;

import model.Batch;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchDAO {
    public List<Batch> getAllBatches() {
        List<Batch> batches = new ArrayList<>();
        String query = "SELECT * FROM batches";
        try (Connection connection = DBConnectionUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Batch batch = new Batch();
                batch.setBatchId(resultSet.getInt("batch_id"));
                batch.setBatchName(resultSet.getString("batch_name"));
                batch.setTiming(resultSet.getString("timing"));
                batches.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }

    public void saveBatch(Batch batch) {
        String query = "INSERT INTO batches (batch_name, timing) VALUES (?, ?)";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, batch.getBatchName());
            preparedStatement.setString(2, batch.getTiming());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement update and delete methods similarly
}
