// This is an abstract class that represents a tutoring session
public abstract class Session {

    // Unique ID for each session
    private int sessionId;

    // The student and mentor involved in the session
    private Student student;
    private Mentor mentor;

    // Date and time of the session
    private String dateTime;

    // Static variable to auto-generate session IDs
    private static int nextSessionId = 1;

    // Constructor to create a session
    public Session(Student student, Mentor mentor, String dateTime) {
        this.student = student;
        this.mentor = mentor;
        // Assign a unique session ID and increment for the next one
        this.sessionId = nextSessionId++;
        this.dateTime = dateTime;
    }

    // Getter for session ID
    public int getSessionId() {
        return sessionId;
    }

    // Getter for the student
    public Student getStudent() {
        return student;
    }

    // Getter for the mentor
    public Mentor getMentor() {
        return mentor;
    }

    // Getter for date and time of the session
    public String getDateTime() {
        return dateTime;
    }

    // Abstract method to get the session type (like "Subject 1-to-1")
    public abstract String getType();

    // Abstract method to get details (like subject name or topic)
    public abstract String getDetails();

    // String format of session info
    @Override
    public String toString() {
        return "Session#" + sessionId + " (" + getType() + " - " + getDetails() +
                ") [Student: " + student.getName() + ", Mentor: " + mentor.getName() + "]";
    }
}
