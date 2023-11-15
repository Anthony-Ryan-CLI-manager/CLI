import java.util.ArrayList;

public class ContactsManager {

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
            for (Contact contact : contacts) {
                System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
            }
        }
    }


}
