import java.util.Scanner;

public class Main {
    //not working
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add a new contact.");
            System.out.println("2. View Contacts.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit: Close the application and save your contacts to a file (contacts.txt).\n");
        }
    }
}

