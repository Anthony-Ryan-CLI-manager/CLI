import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ContactsManager {
        private List<Contact> contacts = new ArrayList<>();

        public void addContact(Contact contact) {contacts.add(contact);
            updateFile();
        }

        public void deleteContact(String name) {
            contacts.removeIf(contact -> contact.getName().equals(name));
            updateFile();
        }

        public Contact searchContact(String name) {
            return contacts.stream()
                    .filter(contact -> contact.getName().equals(name))
                    .findFirst()
                    .orElse(null);
        }

    public void displayContacts() {
        // Print the header
        System.out.println("Name | Phone Number");
        System.out.println("---------------------------------------");
        try {
            Path filePath = Paths.get("data/contacts.txt");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFile() {
        try {
            List<String> lines = new ArrayList<>();
            for (Contact contact : contacts) {
                lines.add(contact.getName() + ": " + contact.getPhoneNumber());
            }
            Files.write(filepath, lines);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating contacts file", e);
        }
    }
}



