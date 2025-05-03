import java.util.*;

public class BookingPlatform {

    private static List<Student> students = new ArrayList<>();
    private static List<Mentor> mentors = new ArrayList<>();
    private static List<Session> sessions = new ArrayList<>();
    private static Map<Integer, User> userMap = new HashMap<>();
    private static Map<Integer, Session> sessionMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Peer Tutor Booking Platform ===");
            System.out.println("1. Register new Student");
            System.out.println("2. Register new Mentor");
            System.out.println("3. List all Students");
            System.out.println("4. List all Mentors");
            System.out.println("5. Book a Session");
            System.out.println("6. List all Sessions");
            System.out.println("7. Cancel a Session");
            System.out.println("8. Exit");
            System.out.print("Enter an option (1-8): ");

            String input = sc.nextLine().trim();

            switch (input) {
                case "1" -> {
                    // Register Student
                    String name = getValidatedInput(sc, "Enter student's name: ");
                    String major = getValidatedInput(sc, "Enter student's major: ");
                    int graduationYear = getValidatedIntInput(sc, "Enter student's graduation year: ", 2024, 2100);

                    Student student = new Student(name, major, graduationYear);
                    students.add(student);
                    userMap.put(student.getId(), student);
                    System.out.println("Student registered with ID: " + student.getId());
                }
                case "2" -> {
                    // Register Mentor
                    String name = getValidatedInput(sc, "Enter mentor's name: ");
                    String expertise = getValidatedInput(sc, "Enter mentor's expertise: ");

                    Mentor mentor = new Mentor(name, expertise);
                    mentors.add(mentor);
                    userMap.put(mentor.getId(), mentor);
                    System.out.println("Mentor registered with ID: " + mentor.getId());
                }
                case "3" -> {
                    // List Students
                    if (students.isEmpty()) {
                        System.out.println("No students registered.");
                    } else {
                        System.out.println("Registered Students:");
                        students.forEach(s -> System.out.println(" - " + s));
                    }
                }
                case "4" -> {
                    // List Mentors
                    if (mentors.isEmpty()) {
                        System.out.println("No mentors registered.");
                    } else {
                        System.out.println("Registered Mentors:");
                        mentors.forEach(m -> System.out.println(" - " + m));
                    }
                }
                case "5" -> {
                    // Book a Session
                    int studentId = getValidatedIntInput(sc, "Enter Student ID: ", 1, Integer.MAX_VALUE);
                    int mentorId = getValidatedIntInput(sc, "Enter Mentor ID: ", 1, Integer.MAX_VALUE);

                    if (!userMap.containsKey(studentId) || !(userMap.get(studentId) instanceof Student)) {
                        System.out.println("Invalid Student ID.");
                        continue;
                    }

                    if (!userMap.containsKey(mentorId) || !(userMap.get(mentorId) instanceof Mentor)) {
                        System.out.println("Invalid Mentor ID.");
                        continue;
                    }

                    Student student = (Student) userMap.get(studentId);
                    Mentor mentor = (Mentor) userMap.get(mentorId);

                    System.out.println("Select session type:");
                    System.out.println("1. Subject 1-to-1");
                    System.out.println("2. Career Coaching");
                    String sessionChoice = sc.nextLine().trim();

                    if (!sessionChoice.equals("1") && !sessionChoice.equals("2")) {
                        System.out.println("Invalid session type.");
                        continue;
                    }

                    String dateTime = getValidatedInput(sc, "Enter date and time for the session (e.g., '2025-06-01 14:00'): ");
                    Session session = null;

                    if (sessionChoice.equals("1")) {
                        String subject = getValidatedInput(sc, "Enter subject name: ");
                        session = new SubjectSession(student, mentor, subject, dateTime);
                    } else {
                        String topic = getValidatedInput(sc, "Enter coaching topic: ");
                        session = new CareerSession(student, mentor, topic, dateTime);
                    }

                    sessions.add(session);
                    sessionMap.put(session.getSessionId(), session);
                    student.notify("Session ID " + session.getSessionId() + " booked.");
                    mentor.notify("New session with Student " + student.getName() + " (Session ID " + session.getSessionId() + ").");
                    System.out.println("Session booked successfully.");
                    System.out.println(session);
                }
                case "6" -> {
                    // List Sessions
                    if (sessions.isEmpty()) {
                        System.out.println("No sessions have been booked yet.");
                    } else {
                        System.out.println("Scheduled Sessions:");
                        sessions.forEach(sess -> System.out.println(" - " + sess));
                    }
                }
                case "7" -> {
                    // Cancel Session
                    int sessionId = getValidatedIntInput(sc, "Enter Session ID to cancel: ", 1, Integer.MAX_VALUE);
                    Session sessionToCancel = sessionMap.get(sessionId);

                    if (sessionToCancel == null) {
                        System.out.println("No session found with ID: " + sessionId);
                        continue;
                    }

                    sessions.remove(sessionToCancel);
                    sessionMap.remove(sessionId);
                    sessionToCancel.getStudent().notify("Your session (ID " + sessionId + ") has been cancelled.");
                    sessionToCancel.getMentor().notify("Session (ID " + sessionId + ") with student " +
                            sessionToCancel.getStudent().getName() + " has been cancelled.");
                    System.out.println("Session " + sessionId + " cancelled successfully.");
                }
                case "8" -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please enter a number from 1 to 8.");
            }
        }

        sc.close();
    }

    // === Utility Methods for Validation ===

    private static String getValidatedInput(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    private static int getValidatedIntInput(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val < min || val > max) {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
