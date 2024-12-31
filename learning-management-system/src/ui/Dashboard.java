package ui;

import dao.CourseDAO;
import models.Course;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Dashboard extends JFrame {
    private CourseDAO courseDAO;

    public Dashboard() {
        courseDAO = new CourseDAO();
        initialize();
    }

    private void initialize() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add components and layout here...

        // Example to fetch and display all courses
        try {
            List<Course> courses = courseDAO.getAllCourses();
            // Display courses in the UI (you could use a JTable, for example)
            for (Course course : courses) {
                System.out.println("Course Name: " + course.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load courses.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        });
    }
}
