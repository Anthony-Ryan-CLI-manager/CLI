import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ContactsManager {
    private Map<String, String> contacts;
    private Path filepath;

    public ContactsManager() {
        this.contacts = loadContacts(filepath); // Load contacts from the file
        this.filepath = filepath;
    }

    private Map<String, String> loadContacts(Path filepath) {
        try {
            if (Files.exists(filepath)) {
                return Files.lines(filepath)
                        .map(line -> line.split("\\s*:\\s*"))
                        .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public void displayContacts() {
        contacts.forEach((name, phoneNumber) -> System.out.println(name + " | " + formatPhoneNumber(phoneNumber)));
    }

    public void addContact(Contact contact) {
        String existingContact = contacts.get(contact.getName());
        if (existingContact == null || askToOverwrite()) {
            contacts.put(contact.getName(), contact.getPhoneNumber());
            ContactWriter.writeContactsToFile(contacts, filepath);
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Contact not added.");
        }
    }

    public Contact searchContact(String name) {
        String phoneNumber = contacts.get(name);
        if (phoneNumber != null) {
            return new Contact(name, phoneNumber);
        }
        return null;
    }

    public void deleteContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            ContactWriter.writeContactsToFile(contacts, filepath);
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }
        private boolean askToOverwrite() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("There's already a contact with this name. Do you want to overwrite it? (Yes/No): ");
            String response = scanner.nextLine().toLowerCase();
            return response.equals("yes");
        }
    }