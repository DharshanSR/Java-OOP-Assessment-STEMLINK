public class CareerSession extends Session{

    private String topic;

    public CareerSession (Student student, Mentor mentor, String topic, String dateTime) {
        super(student, mentor, dateTime);
        this.topic = topic;
    }

    @Override
    public String getType() {
        return "Career Coaching";
    }

    @Override
    public String getDetails() {
        return "Topic: " + topic;
    }
}
