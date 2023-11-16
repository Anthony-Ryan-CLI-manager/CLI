import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            ContactsManager manager = new ContactsManager();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. View contacts.");
                System.out.println("2. Add a new contact.");
                System.out.println("3. Search a contact by name.");
                System.out.println("4. Delete an existing contact.");
                System.out.println("5. Exit.");
                System.out.print("Enter an option (1, 2, 3, 4 or 5): ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        manager.displayContacts();
                        break;
                    case 2:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter phone number: ");
                        String phoneNumber = scanner.nextLine();
                        manager.addContact(new Contact(name, phoneNumber));
                        break;
                    case 3:
                        System.out.print("Enter name to search: ");
                        name = scanner.nextLine();
                        Contact contact = manager.searchContact(name);
                        if (contact != null) {
                            System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
                        } else {
                            System.out.println("Contact not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter name to delete: ");
                        name = scanner.nextLine();
                        manager.deleteContact(name);
                        break;
                    case 5:
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }


