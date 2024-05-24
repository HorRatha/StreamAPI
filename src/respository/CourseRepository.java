package repository;

import exception.CourseNotFoundException;
import model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private List<Course> courses;

    public CourseRepository() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Course findCourseById(int id) throws CourseNotFoundException {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new CourseNotFoundException("Course with ID " + id + " not found.");
    }

    public Course findCourseByTitle(String title) throws CourseNotFoundException {
        for (Course course : courses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                return course;
            }
        }
        throw new CourseNotFoundException("Course with title \"" + title + "\" not found.");
    }

    public void renameCourse(int id, String newTitle) throws CourseNotFoundException {
        Course course = findCourseById(id);
        course.setTitle(newTitle);
    }
}
