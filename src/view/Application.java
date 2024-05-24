package view;

import exception.CourseNotFoundException;
import exception.InvalidInputException;
import model.Course;
import service.CourseService;
import repository.CourseRepository;

import java.util.Arrays;
import java.util.Date; // Import Date class from java.util package
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat; // Import SimpleDateFormat for date parsing
import java.text.ParseException; // Import ParseException for parsing exceptions

public class Application {
    private static final CourseService courseService = new CourseService(new CourseRepository());
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add new course");
            System.out.println("2. List all courses");
            System.out.println("3. Find course by ID");
            System.out.println("4. Find course by Title");
            System.out.println("5. Rename Course");
            System.out.println("0/99. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addNewCourse();
                    break;
                case 2:
                    listAllCourses();
                    break;
                case 3:
                    findCourseById();
                    break;
                case 4:
                    findCourseByTitle();
                    break;
                case 5:
                    renameCourse();
                    break;
                case 0:
                case 99:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
                    break;
            }
        }
    }

    private static void addNewCourse() {
        try {
            System.out.print("Input course title: ");
            String title = scanner.nextLine();
            System.out.print("Input instructor names (comma-separated): ");
            List<String> instructorNames = Arrays.asList(scanner.nextLine().split(","));
            System.out.print("Input instructor requirements (comma-separated): ");
            List<String> instructorRequirements = Arrays.asList(scanner.nextLine().split(","));
            System.out.print("Input course start date (yyyy-MM-dd): ");
            String dateString = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(dateString);
            courseService.addCourse(title, instructorNames, instructorRequirements, startDate);
            System.out.println("Course added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }


    private static void listAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        System.out.println("All Courses:");
        for (Course course : courses) {
            System.out.println(course.getId() + "\t" + course.getTitle() + "\t" + course.getInstructorNames() +
                    "\t" + course.getInstructorRequirements());
        }
    }

//    private static void findCourseById() {
//        System.out.print("Enter course ID: ");
//        int id = scanner.nextInt();
//        scanner.nextLine(); // Consume newline character
//        try {
//            Course course = courseService.findCourseById(id);
//            System.out.println("Course found: " + course.getTitle());
//        } catch (CourseNotFoundException e) {
//            System.out.println("Course not found: " + e.getMessage());
//        }
//    }
//
//    private static void findCourseByTitle() {
//        System.out.print("Enter course title: ");
//        String title = scanner.nextLine();
//        try {
//            Course course = courseService.findCourseByTitle(title);
//            System.out.println("Course found: " + course.getId());
//        } catch (CourseNotFoundException e) {
//            System.out.println("Course not found: " + e.getMessage());
//        }
//    }
private static void findCourseById() {
    System.out.print("Enter course ID: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consume newline character
    try {
        Course course = courseService.findCourseById(id);
        System.out.println("Course found:");
        displayCourseInfo(course);
    } catch (CourseNotFoundException e) {
        System.out.println("Course not found: " + e.getMessage());
    }
}

    private static void findCourseByTitle() {
        System.out.print("Enter course title: ");
        String title = scanner.nextLine().toLowerCase(); // Convert input to lowercase
        try {
            Course course = courseService.findCourseByTitle(title);
            System.out.println("Course found:");
            displayCourseInfo(course);
        } catch (CourseNotFoundException e) {
            System.out.println("Course not found: " + e.getMessage());
        }
    }

    private static void displayCourseInfo(Course course) {
        System.out.println("ID\tTitle\tInstructor Names\tInstructor Requirements\tStart Date");
        System.out.println(course.getId() + "\t" + course.getTitle() + "\t" +
                String.join(",", course.getInstructorNames()) + "\t" +
                String.join(",", course.getInstructorRequirements()) + "\t" +
                course.getStartDate());
    }


    private static void renameCourse() {
        try {
            System.out.print("Enter course ID to rename: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();
            courseService.renameCourse(id, newTitle);
            System.out.println("Course renamed successfully.");
        } catch (CourseNotFoundException e) {
            System.out.println("Course not found: " + e.getMessage());
        }
    }
}
