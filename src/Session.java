public abstract class Session {

    private int sessionId;
    private Student student;
    private Mentor mentor;
    private String dateTime;

    private static int nextSessionId = 1;

    public Session (Student student, Mentor mentor,String dateTime) {
        this.student = student;
        this.mentor = mentor;
        this.sessionId = nextSessionId++;
        this.dateTime = dateTime;
    }

    public int getSessionId() {
        return sessionId;
    }

    public Student getStudent() {
        return student;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public String getDateTime() {
        return dateTime;
    }

    public abstract String getType();
    public abstract String getDetails();

    @Override
    public String toString() {
        return "Session#" + sessionId + " (" + getType() + " - " + getDetails() +
                ") [Student: " + student.getName() + ", Mentor: " + mentor.getName() + "]";
    }
}
