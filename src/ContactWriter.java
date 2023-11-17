import java.nio.file.*;
import java.util.*;
import java.util.logging.*;
import java.util.Map;


public class ContactWriter {
    public static final Logger logger = Logger.getLogger(ContactWriter.class.getName());

    public static void writeContactsToFile(Map<String, String> contacts, Path filepath) {
        try {
            if (Files.notExists(filepath.getParent())) {
                Files.createDirectories(filepath.getParent());
            }

            List<String> lines = new ArrayList<>();
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                String line = entry.getKey() + " | " + entry.getValue();
                lines.add(line);
            }
            Files.write(filepath, lines);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error writing contacts to file", e);
        }
    }

    public static void main(String[] args) {
        Map<String, String> contacts = new HashMap<>();
        contacts.put("Joe", "123-456-7890");
        contacts.put("Sue", "987-654-3210");
        contacts.put("Jill", "555-123-4567");

        Path filepath = Paths.get("data", "contacts.txt");

        try {
            if (Files.notExists(Paths.get("data"))) {
                Files.createDirectories(Paths.get("data"));
            }

            writeContactsToFile(contacts, filepath);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error writing contacts to file", e);
        }
    }
}
