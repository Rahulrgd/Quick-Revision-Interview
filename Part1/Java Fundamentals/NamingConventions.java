// Example following Java naming conventions and good code organization

// Class name in PascalCase
public class StudentRecord {

    // Constant in UPPER_CASE (usually for fixed values)
    public static final int MAX_STUDENTS = 100;

    // Instance variables in camelCase
    private String studentName;
    private int studentAge;
    private double studentGPA;

    // Constructor with parameters in camelCase
    public StudentRecord(String studentName, int studentAge, double studentGPA) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentGPA = studentGPA;
    }

    // Method name in camelCase
    public void displayStudentInfo() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Student Age: " + studentAge);
        System.out.println("Student GPA: " + studentGPA);
    }

    // Getter for studentName
    public String getStudentName() {
        return studentName;
    }

    // Setter for studentName
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    // Main method to test the class
    public static void main(String[] args) {
        // Local variable name in camelCase
        StudentRecord student = new StudentRecord("Rahul", 21, 3.8);
        student.displayStudentInfo();
    }
}
