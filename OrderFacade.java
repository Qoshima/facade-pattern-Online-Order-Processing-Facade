import java.util.List;

public class OrderFacade {
    private InventoryService inventory = new InventoryService();
    private PaymentProcessor payment = new PaymentProcessor();
    private ShippingService shipping = new ShippingService();
    private NotificationService notification = new NotificationService();

    public void placeOrder(List<CartItem> cartItems, Customer customer) {
        System.out.println("Customer: " + customer.getId());
        System.out.println("Items in cart: " + cartItems.size());
        inventory.reserveStock();
        payment.processPayment();
        shipping.shipOrder();
        notification.sendConfirmation();
    }
}