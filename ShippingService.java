public class ShippingService {
    private boolean shipped = false;

    public boolean shipOrder(String address) {
        if ("Astana".equalsIgnoreCase(address)) {
            shipped = true;
            System.out.println("Order shipped to " + address);
            return true;
        }
        System.out.println("Shipping failed");
        return false;
    }
    public void cancelShipment() {
        if (shipped) {
            shipped = false;
            System.out.println("Shipment cancelled");
        }
    }
}