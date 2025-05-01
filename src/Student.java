public class Student extends User implements Notifiable{

    private String major;
    private int graduationYear;

    public Student (String name, String major, int graduationYear) {
        super(name);
        this.major = major;
        this.graduationYear = graduationYear;
    }

    public String getMajor() {
        return major;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student[ID=" + getId() + ", Name=" + getName() +
                ", Major=" + major + ", GraduationYear=" + graduationYear + "]";
    }

    @Override
    public void notify(String message) {
        System.out.println("Notification for Student " + getName() + ": " + message);
    }

}
