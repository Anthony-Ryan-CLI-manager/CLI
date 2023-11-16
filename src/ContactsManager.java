import java.util.ArrayList;
import java.util.List;


public class ContactsManager {
        private List<Contact> contacts = new ArrayList<>();

        public void addContact(Contact contact) {
            contacts.add(contact);
        }

        public void deleteContact(String name) {
            contacts.removeIf(contact -> contact.getName().equals(name));
        }

        public Contact searchContact(String name) {
            return contacts.stream()
                    .filter(contact -> contact.getName().equals(name))
                    .findFirst()
                    .orElse(null);
        }

    public void displayContacts() {
        // Print the header
        System.out.printf("%-15s | %-15s |%n----------------------------------" +
                "%n", "Name", "Phone number");

        // Print the data
        for (Contact contact : contacts) {
            System.out.printf("%-15s | %-15s |%n", contact.getName(), contact.getPhoneNumber());
        }
    }

}



