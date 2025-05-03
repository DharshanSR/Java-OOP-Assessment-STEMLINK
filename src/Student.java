// This class represents a Student
// It inherits from the User class and implements the Notifiable interface
public class Student extends User implements Notifiable {

    // The student's major (like Computer Science, Biology, etc.)
    private String major;

    // The year the student is expected to graduate
    private int graduationYear;

    // Constructor to create a new Student
    public Student(String name, String major, int graduationYear) {
        // Call the constructor from the User class to set the name and ID
        super(name);
        // Set the major and graduation year
        this.major = major;
        this.graduationYear = graduationYear;
    }

    // Getter method to get the student's major
    public String getMajor() {
        return major;
    }

    // Getter method to get the student's graduation year
    public int getGraduationYear() {
        return graduationYear;
    }

    // This method tells what role the user has
    @Override
    public String getRole() {
        return "Student";
    }

    // Returns a string with student info in a nice format
    @Override
    public String toString() {
        return "Student[ID=" + getId() + ", Name=" + getName() +
                ", Major=" + major + ", GraduationYear=" + graduationYear + "]";
    }

    // This method is called to notify the student (like sending a message)
    @Override
    public void notify(String message) {
        System.out.println("Notification for Student " + getName() + ": " + message);
    }
}
