package Task24;
import java.io.*;
import java.util.Scanner;

public class Task4 {

    static final String NOTES_FOLDER = "notes";
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File folder = new File(NOTES_FOLDER);
        if (!folder.exists()) folder.mkdir(); 

        while (true) {
            System.out.println("\n=== My Notes App ===");
            System.out.println("1. Create a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Delete a note");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int click = sc.nextInt();
            sc.nextLine(); 

            switch (click) {
                case 1:
                    createNote();
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    
    public static void createNote() {
        try {
            System.out.print("Enter note title (no spaces): ");
            String title = sc.nextLine();
            FileWriter writer = new FileWriter(NOTES_FOLDER + "/" + title + ".txt");

            System.out.println("Enter note content (type 'END' to finish):");
            String line;
            while (!(line = sc.nextLine()).equals("END")) {
                writer.write(line + System.lineSeparator());
            }

            writer.close();
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note.");
        }
    }

    
    public static void viewNotes() {
        File folder = new File(NOTES_FOLDER);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No notes found.");
            return;
        }

        for (File file : files) {
            System.out.println("\n📄 " + file.getName().replace(".txt", ""));
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("  " + line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + file.getName());
            }
        }
    }

    
    public static void deleteNote() {
        System.out.print("Enter the title of the note to delete: ");
        String title = sc.nextLine();
        File file = new File(NOTES_FOLDER + "/" + title + ".txt");

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Note deleted successfully.");
            } else {
                System.out.println("Failed to delete the note.");
            }
        } else {
            System.out.println("Note not found.");
        }
    }
}
