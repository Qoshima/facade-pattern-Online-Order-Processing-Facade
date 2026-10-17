public class ShippingService {
    public boolean shipOrder(String address) {
        if ("Astana".equalsIgnoreCase(address)) {
            System.out.println("Order shipped to " + address);
            return true;
        }
        System.out.println("Shipping failed");
        return false;
    }
}