import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContactsManager {
    private static final Logger LOGGER = Logger.getLogger(ContactWriter.class.getName());
    private List<Contact> contacts = new ArrayList<>();
    private Path filePath = Paths.get("data/contacts.txt");

    public ContactsManager(List<Contact> contacts, String filePath) {
        this.contacts = contacts;
        this.filePath = Path.of(filePath);
    }

    public ContactsManager() {

    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        updateFile();
    }
//        updateFile();


//            else everything


    public void updateFile() {
        try {
            List<String> lines = new ArrayList<>();
            for (Contact contact : contacts) {
                lines.add(contact.getName() + ": " + contact.getPhoneNumber());
            }
            Path file = Paths.get(filePath.toUri());
            Files.write(file, lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            Logger LOGGER = Logger.getLogger(getClass().getName());
            LOGGER.log(Level.SEVERE, "Error updating contacts file", e);
        }
    }

    public void deleteContact(String name) {
        contacts.removeIf(contact -> contact.getName().equals(name));
        updateFile();
    }
//try readAllLines in the files class which will return a list and then get it to read from the list.
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
                    if (parts.length < 2) {
                        System.out.println("Invalid contact format" + line);
                        continue;

                    }
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


    }




