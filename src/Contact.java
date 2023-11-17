public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return formatPhoneNumber(phoneNumber);
    }

    private String formatPhoneNumber(String phoneNumber) {
        String digitsOnly = phoneNumber.replaceAll("[0-9]", "");
        if (digitsOnly.length() == 10) {
            return digitsOnly.substring(0, 3) + "-" + digitsOnly.substring(3, 6) + "-" + digitsOnly.substring(6);
        } else if (digitsOnly.length() == 7) {
            return digitsOnly.substring(0, 3) + "-" + digitsOnly.substring(3);
        } else {
            return phoneNumber;
        }
    }
}
