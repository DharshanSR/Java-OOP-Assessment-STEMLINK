public abstract class User {
    private int id;
    private String name;
    private static int nextId = 1;

    public User (String name) {
        this.id = nextId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + "[ID = " + id + ", Name = " + name + "]";
    }
}
