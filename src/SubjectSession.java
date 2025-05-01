public class SubjectSession extends Session{

    private String subjectName;

    public SubjectSession (Student student, Mentor mentor, String subjectName, String dateTime) {
        super(student, mentor, dateTime);
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public String getType() {
        return "Subject 1-to-1";
    }

    @Override
    public String getDetails() {
        return "Subject: " + subjectName;
    }
}
