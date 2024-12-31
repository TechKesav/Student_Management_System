package dao;

import models.Course;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // Method to add a new course to the database
    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name, description, instructor_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getDescription());
            stmt.setInt(3, course.getInstructorId());
            stmt.executeUpdate();
            System.out.println("Course added successfully!");
        }
    }

    // Method to fetch a course by name
    public Course getCourseByName(String name) throws SQLException {
        String sql = "SELECT * FROM courses WHERE name = ?";
        Course course = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                int instructorId = rs.getInt("instructor_id");

                course = new Course(id, name, description, instructorId);
            }
        }

        return course; // Return the course object, or null if not found
    }

    // Method to fetch all courses from the database
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int instructorId = rs.getInt("instructor_id");

                Course course = new Course(id, name, description, instructorId);
                courses.add(course);
            }
        }

        return courses; // Return the list of courses
    }
}
