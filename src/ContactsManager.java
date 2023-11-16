import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;



public class ContactsManager {
        private final List<Contact> contacts = new ArrayList<>();
        private final Path filePath = Paths.get("data/contacts.txt");

        public void addContact(Contact contact) {contacts.add(contact);
//            updateFile(); We need to fix the updateFile() because it is adding the new name and removing everything
//            else everything
        }

        public void deleteContact(String name) {
            contacts.removeIf(contact -> contact.getName().equals(name));
//        updateFile(); We need to fix the updateFile() because it is deleting everything
        }

        public Contact searchContact(String name) {
            return contacts.stream()
                    .filter(contact -> contact.getName().equals(name))
                    .findFirst()
                    .orElse(null);
        }

    public void displayContacts() {
        System.out.printf("%-15s | %-15s |%n", "Name", "Phone number");
        System.out.println("----------------------------------------");

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(": ", 2);
                String name = parts[0];
                String phoneNumber = parts[1];
                System.out.printf("%-15s | %-15s |%n", name, phoneNumber);
            }
        } catch (IOException e) {
            System.out.println("Uh oh, something went wrong: " + e.getMessage());
            System.out.println("Here is some more detail:");
            e.printStackTrace();
        }
    }

    private void updateFile() {
        try {
            List<String> lines = new ArrayList<>();
            for (Contact contact : contacts) {
                lines.add(contact.getName() + ": " + contact.getPhoneNumber());
            }
            Files.write(filePath, lines);
        } catch (Exception e) {
            ContactWriter.logger.log(Level.SEVERE, "Error updating contacts file", e);
        }
    }
}



