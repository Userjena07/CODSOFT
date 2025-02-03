import java.util.ArrayList;

public class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.enrollStudent(this)) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent(this);
            return true;
        }
        return false;
    }

    public void displayRegisteredCourses() {
        System.out.println("\nRegistered Courses for " + name + " (" + studentID + "):");
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course.getCourseCode() + " - " + course.getTitle());
            }
        }
    }
}
