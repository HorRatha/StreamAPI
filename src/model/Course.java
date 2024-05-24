package model;

import java.util.Date;
import java.util.List;

public class Course {
    private static int nextId = 1;

    private int id;
    private String title;
    private List<String> instructorNames;
    private List<String> instructorRequirements;
    private Date startDate;

    public Course(String title, List<String> instructorNames, List<String> instructorRequirements, Date startDate) {
        this.id = nextId++;
        this.title = title;
        this.instructorNames = instructorNames;
        this.instructorRequirements = instructorRequirements;
        this.startDate = startDate;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getInstructorNames() {
        return instructorNames;
    }

    public void setInstructorNames(List<String> instructorNames) {
        this.instructorNames = instructorNames;
    }

    public List<String> getInstructorRequirements() {
        return instructorRequirements;
    }

    public void setInstructorRequirements(List<String> instructorRequirements) {
        this.instructorRequirements = instructorRequirements;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
