import dao.EnrollmentDAO;
import dao.UserDAO;
import dao.CourseDAO;
import models.User;
import models.Course;
import models.Enrollment;
import util.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    private static UserDAO userDAO;
    private static CourseDAO courseDAO;
    private static EnrollmentDAO enrollmentDAO;
    private static JFrame frame;

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connected to the database successfully!");
            initializeDAOs();

            SwingUtilities.invokeLater(() -> {
                frame = new JFrame("Course Enrollment System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 400);
                frame.setLayout(new BorderLayout());

                showMainMenu();
                frame.setVisible(true);
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
        }
    }

    private static void initializeDAOs() {
        userDAO = new UserDAO();
        courseDAO = new CourseDAO();
        enrollmentDAO = new EnrollmentDAO();
    }

    private static void showMainMenu() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton registerUserButton = new JButton("Register User");
        JButton createCourseButton = new JButton("Create Course");
        JButton enrollButton = new JButton("Process Enrollment");

        registerUserButton.addActionListener(e -> showUserRegistrationForm());
        createCourseButton.addActionListener(e -> showCourseCreationForm());
        enrollButton.addActionListener(e -> showEnrollmentForm());

        panel.add(registerUserButton);
        panel.add(createCourseButton);
        panel.add(enrollButton);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private static void showUserRegistrationForm() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField roleField = new JTextField();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String role = roleField.getText().trim();

            User user = new User(0, username, password, role);
            try {
                userDAO.registerUser(user);
                JOptionPane.showMessageDialog(frame, "User registered successfully!");
                showMainMenu();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error during user registration: " + ex.getMessage());
            }
        });

        panel.add(registerButton);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private static void showCourseCreationForm() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField courseNameField = new JTextField();
        JTextField courseDescriptionField = new JTextField();
        JTextField instructorIdField = new JTextField();

        panel.add(new JLabel("Course Name:"));
        panel.add(courseNameField);
        panel.add(new JLabel("Course Description:"));
        panel.add(courseDescriptionField);
        panel.add(new JLabel("Instructor ID:"));
        panel.add(instructorIdField);

        JButton createButton = new JButton("Create Course");
        createButton.addActionListener(e -> {
            String courseName = courseNameField.getText().trim();
            String courseDescription = courseDescriptionField.getText().trim();
            int instructorId = Integer.parseInt(instructorIdField.getText().trim());

            Course course = new Course(0, courseName, courseDescription, instructorId);
            try {
                courseDAO.addCourse(course);
                JOptionPane.showMessageDialog(frame, "Course created successfully!");
                showMainMenu();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error during course creation: " + ex.getMessage());
            }
        });

        panel.add(createButton);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private static void showEnrollmentForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField userIdField = new JTextField();
        JTextField courseIdField = new JTextField();

        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("Course ID:"));
        panel.add(courseIdField);

        JButton enrollButton = new JButton("Enroll");
        enrollButton.addActionListener(e -> {
            int userId = Integer.parseInt(userIdField.getText().trim());
            int courseId = Integer.parseInt(courseIdField.getText().trim());

            Enrollment enrollment = new Enrollment(0, userId, courseId, new Date());
            try {
                enrollmentDAO.addEnrollment(enrollment);
                JOptionPane.showMessageDialog(frame, "Enrollment completed successfully!");
                showMainMenu();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error during enrollment: " + ex.getMessage());
            }
        });

        panel.add(enrollButton);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
