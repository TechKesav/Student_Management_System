package dao;

import models.Enrollment;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EnrollmentDAO {
    public void addEnrollment(Enrollment enrollment) throws SQLException {
        String query = "INSERT INTO enrollments (enrollmentId, userId, courseId, enrollmentDate) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, enrollment.getEnrollmentId());
            statement.setInt(2, enrollment.getUserId());
            statement.setInt(3, enrollment.getCourseId());
            statement.setDate(4, new java.sql.Date(enrollment.getEnrollmentDate().getTime()));
            statement.executeUpdate();
        }
    }

    public Enrollment getEnrollmentById(int enrollmentId) throws SQLException {
        String query = "SELECT * FROM enrollments WHERE enrollmentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, enrollmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                int courseId = resultSet.getInt("courseId");
                Date enrollmentDate = resultSet.getDate("enrollmentDate");
                return new Enrollment(enrollmentId, userId, courseId, enrollmentDate);
            }
        }
        return null;
    }
}