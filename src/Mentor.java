public class Mentor extends User implements Notifiable{

    private String expertise;

    public Mentor(String name, String expertise) {
        super(name);
        this.expertise = expertise;
    }

    public String getExpertise(){
        return expertise;
    }

    @Override
    public String getRole(){
        return "Mentor";
    }

    @Override
    public String toString() {
        return "Mentor[ID=" + getId() + ", Name=" + getName() +
                ", Expertise=" + expertise + "]";
    }

    @Override
    public void notify(String message) {
        System.out.println("Notification for Mentor " + getName() + ": " + message);
    }

}
