# Contacts Manager CLI Application

Welcome to the Contacts Manager CLI, a command-line application for managing your contacts!

## Overview

This application allows you to perform the following actions:

1. **View Contacts:** Display a list of all your contacts.
2. **Add a New Contact:** Add a new contact with a name and phone number.
3. **Search Contacts:** Find a contact by name.
4. **Delete a Contact:** Remove an existing contact.
5. **Exit:** Close the application and save your contacts to a file (`contacts.txt`).

## Functionality Details

- The application stores contact information in a file (`contacts.txt`) for persistence between runs.
- Each contact entry in the file contains one contact per line.
- On startup, the application reads the contact list from the file.
- Before exiting, the application rewrites the `contacts.txt` file with the updated contact information.

## Usage

1. Clone the repository.
2. Compile and run the application.
3. Follow the on-screen menu to interact with your contacts.

## Bonus Features

### Phone Number Formatting

- Phone numbers are formatted with dashes for readability.

### International Phone Numbers

- Support for different phone number lengths, including international formats.

### Overwrite Warning

- If you attempt to add a contact with an existing name, the application will prompt you to confirm overwrite.

### Formatted Output

- Contacts are displayed in a well-formatted table for easy readability.

## Getting Started

To get started with the Contacts Manager CLI:

```bash
$ git clone <repository-url>
$ cd contacts-manager-cli
$ javac ContactsManager.java
$ java ContactsManager
