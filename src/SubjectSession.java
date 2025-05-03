public class SubjectSession extends Session {

    // The name of the subject for this session
    private String subjectName;

    // Constructor to create a new SubjectSession
    public SubjectSession(Student student, Mentor mentor, String subjectName, String dateTime) {
        // Call the constructor of the parent class (Session)
        super(student, mentor, dateTime);
        // Set the subject name for this session
        this.subjectName = subjectName;
    }

    // Getter method to get the subject name
    public String getSubjectName() {
        return subjectName;
    }

    // This method overrides getType() from the Session class
    // It tells what type of session this is
    @Override
    public String getType() {
        return "Subject 1-to-1";
    }

    // This method overrides getDetails() from the Session class
    // It returns more info about the session (the subject)
    @Override
    public String getDetails() {
        return "Subject: " + subjectName;
    }
}
