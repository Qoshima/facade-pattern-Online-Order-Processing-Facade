public class Customer {
    private String id;
    private String email;
    private String address;

    public Customer(String id, String email, String address) {
        this.id = id;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
