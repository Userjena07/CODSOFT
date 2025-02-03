import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        runMenu();
    }

    private static void initializeData() {
        courses.add(new Course("CS101", "Introduction to Programming", "Learn basic programming concepts", 3, "MWF 10:00-11:30"));
        courses.add(new Course("MATH201", "Calculus I", "Study of differential and integral calculus", 2, "TTh 9:00-10:30"));
        courses.add(new Course("ENG102", "English Composition", "Improve writing and analytical skills", 4, "MWF 1:00-2:30"));

        students.add(new Student("A001", "Gautam"));
        students.add(new Student("A002", "Lincon"));
        students.add(new Student("A003", "Dinesh"));
    }

    private static void runMenu() {
        while (true) {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            course.displayCourseInfo();
            System.out.println("--------------------");
        }
    }

    private static void registerCourse() {
        Student student = getStudentByID();
        if (student == null)
            return;

        displayCourses();
        System.out.print("Enter course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = getCourseByCode(courseCode);

        if (course != null) {
            if (student.registerCourse(course)) {
                System.out.println("Successfully registered for " + course.getTitle());
            } else {
                System.out.println("Registration failed. Course might be full or already registered.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void dropCourse() {
        Student student = getStudentByID();
        if (student == null)
            return;

        student.displayRegisteredCourses();
        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = getCourseByCode(courseCode);

        if (course != null) {
            if (student.dropCourse(course)) {
                System.out.println("Successfully dropped " + course.getTitle());
            } else {
                System.out.println("Course not found in your registered list.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void viewRegisteredCourses() {
        Student student = getStudentByID();
        if (student != null) {
            student.displayRegisteredCourses();
        }
    }

    private static Student getStudentByID() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
    }

    private static Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
