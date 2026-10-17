import java.util.ArrayList;
import java.util.List;

public class OrderFacade {
    private InventoryService inventory = new InventoryService();
    private PaymentProcessor payment = new PaymentProcessor();
    private ShippingService shipping = new ShippingService();
    private NotificationService notification = new NotificationService();

    public void placeOrder(List<CartItem> cartItems, Customer customer) {
        List<CartItem> reserved = new ArrayList<>();

        for (CartItem item : cartItems) {
            if (!inventory.reserveStock(item.getProductId(), item.getQuantity())) {
                System.out.println("Not enough stock!");

                for (CartItem r : reserved) {
                    inventory.releaseStock(r.getProductId(), r.getQuantity());
                }
                return;
            }

            reserved.add(item);
        }

        System.out.println("Customer: " + customer.getId());
        System.out.println("Items in cart: " + cartItems.size());

        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        if (!payment.processPayment(total)) {
            for (CartItem item : reserved) {
                inventory.releaseStock(item.getProductId(), item.getQuantity());
            }
            return;
        }

        if (!shipping.shipOrder(customer.getAddress())) {
            payment.refundPayment(total);

            for (CartItem item : reserved) {
                inventory.releaseStock(item.getProductId(), item.getQuantity());
            }

            return;
        }
        notification.sendConfirmation();
    }
}