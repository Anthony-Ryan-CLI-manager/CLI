import java.nio.file.*;
import java.util.*;

    public class ContactWriter {
        public static void main(String[] args) {
            Map<String, String> contacts = new HashMap<>();
            contacts.put("Jack Blank", "123-123-1234");
            contacts.put("Sue", "234-234-2345");
            contacts.put("Jill", "345-345-3456");

            Path filepath = Paths.get("data", "contacts.txt");

            try {
                List<String> lines = new ArrayList<>();
                for (Map.Entry<String, String> entry : contacts.entrySet()) {
                    String line = entry.getKey() + ": " + entry.getValue();
                    lines.add(line);
                }
                Files.write(filepath, lines);
            } catch (Exception event) {
                event.printStackTrace();
            }
        }
    }
