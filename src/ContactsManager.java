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
        this.filepath = Paths.get("data", "contacts.txt");
        this.contacts = loadContacts(filepath);
    }

    private Map<String, String> loadContacts(Path filepath) {
        try {
            if (Files.exists(filepath)) {
                return Files.lines(filepath)
                        .map(line -> line.split("\\s*\\|\\s*"))
                        .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public void displayContacts() {
        int maxNameLength = 0;
        int maxPhoneNumberLength = 0;

        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            maxNameLength = Math.max(maxNameLength, entry.getKey().length());
            maxPhoneNumberLength = Math.max(maxPhoneNumberLength, entry.getValue().length());
        }

        System.out.printf("%-" + (maxNameLength + 2) + "s | %-" + (maxPhoneNumberLength + 2) + "s%n", "Name", "Phone number");
        System.out.println(new String(new char[maxNameLength + maxPhoneNumberLength + 25]).replace('\0', '-'));

        int finalMaxNameLength = maxNameLength;
        int finalMaxPhoneNumberLength = maxPhoneNumberLength;

        contacts.forEach((name, phoneNumber) -> {
            Contact contact = new Contact(name, phoneNumber);
            System.out.printf("%-" + (finalMaxNameLength + 2) + "s | %-" + (finalMaxPhoneNumberLength + 2) + "s%n",
                    contact.getName(), contact.getPhoneNumber());
        });
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