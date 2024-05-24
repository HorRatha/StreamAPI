package service;

import exception.CourseNotFoundException;
import exception.InvalidInputException;
import model.Course;
import repository.CourseRepository;

import java.util.Date;
import java.util.List;

public class CourseService {
    private CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void addCourse(String title, List<String> instructorNames, List<String> instructorRequirements, Date startDate) throws InvalidInputException {
        if (title.isBlank() || instructorNames.isEmpty() || instructorRequirements.isEmpty()) {
            throw new InvalidInputException("Title, instructor names, and instructor requirements cannot be empty.");
        }
        Course course = new Course(title, instructorNames, instructorRequirements, startDate);
        repository.addCourse(course);
    }

    public List<Course> getAllCourses() {
        return repository.getAllCourses();
    }

    public Course findCourseById(int id) throws CourseNotFoundException {
        return repository.findCourseById(id);
    }

    public Course findCourseByTitle(String title) throws CourseNotFoundException {
        return repository.findCourseByTitle(title);
    }

    public void renameCourse(int id, String newTitle) throws CourseNotFoundException {
        repository.renameCourse(id, newTitle);
    }
}
