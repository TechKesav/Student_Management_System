// models/Enrollment.java
package models;

import java.util.Date;

public class Enrollment {
    private int enrollmentId;
    private int userId;
    private int courseId;
    private Date enrollmentDate;

    // Constructor with parameters
    public Enrollment(int enrollmentId, int userId, int courseId, Date enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.userId = userId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    // No-argument constructor (if needed)
    public Enrollment() {
    }

    // Getters and Setters
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
