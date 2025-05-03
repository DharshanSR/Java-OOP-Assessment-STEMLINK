public abstract class User {
    // Each user has an ID and a name
    private int id;
    private String name;

    // Static variable to keep track of the next ID to assign
    private static int nextId = 1;

    // Constructor to create a new User
    public User(String name) {
        // Assign a unique ID using nextId
        this.id = nextId;
        // Store the name
        this.name = name;
        // Increase nextId so the next user gets a different ID
        nextId++;
    }

    // Getter method to get the user's ID
    public int getId() {
        return id;
    }

    // Getter method to get the user's name
    public String getName() {
        return name;
    }

    // Abstract method - subclasses will need to say what the role is
    public abstract String getRole();

    // toString method to print info about the user in a nice format
    @Override
    public String toString() {
        return getRole() + "[ID = " + id + ", Name = " + name + "]";
    }
}
