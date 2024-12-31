package models;

public class Course {
    private int id;
    private String name;
    private String description;
    private int instructorId;

    // Constructor
    public Course(int id, String name, String description, int instructorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructorId = instructorId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getInstructorId() {
        return instructorId;
    }
}
