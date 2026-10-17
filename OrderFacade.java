import java.util.ArrayList;
import java.util.List;

public class OrderFacade {
    private InventoryService inventory = new InventoryService();
    private PaymentProcessor payment = new PaymentProcessor();
    private ShippingService shipping = new ShippingService();
    private NotificationService notification = new NotificationService();

    private String currentOrderId;
    private List<CartItem> savedCart;
    private Customer savedCustomer;
    private double savedTotal;
    private int nextId = 1;

    public String placeOrder(List<CartItem> cartItems, Customer customer) {
        if (cartItems == null || cartItems.isEmpty() || customer == null) {
            System.out.println("Invalid order");
            return null;
        }

        for (CartItem cartItem : cartItems) {
            if (cartItem == null || cartItem.getQuantity() <= 0 || cartItem.getPrice() <= 0) {
                System.out.println("Invalid item");
                return null;
            }
        }

        if (currentOrderId != null) {
            System.out.println("An active order already exists!");
            return null;
        }

        List<CartItem> reserved = new ArrayList<>();

        for (CartItem item : cartItems) {
            if (!inventory.reserveStock(item.getProductId(), item.getQuantity())) {
                System.out.println("Not enough stock!");

                for (CartItem r : reserved) {
                    inventory.releaseStock(r.getProductId(), r.getQuantity());
                }
                return null;
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
            return null;
        }

        if (!shipping.shipOrder(customer.getAddress())) {
            payment.refundPayment(total);

            for (CartItem item : reserved) {
                inventory.releaseStock(item.getProductId(), item.getQuantity());
            }

            return null;
        }
        notification.sendConfirmation();

        currentOrderId = "ORD" + nextId++;
        savedCart = new ArrayList<>(cartItems);
        savedCustomer = customer;
        savedTotal = total;

        System.out.println("Order ID: " + currentOrderId);
        return currentOrderId;
    }

    public boolean cancelOrder(String orderId) {
        if (currentOrderId == null || !currentOrderId.equals(orderId)) {
            System.out.println("Order not found!");
            return false;
        }

        shipping.cancelShipment();
        payment.refundPayment(savedTotal);

        for (CartItem item : savedCart) {
            inventory.releaseStock(item.getProductId(), item.getQuantity());
        }

        notification.sendCancellation(savedCustomer.getEmail());

        currentOrderId = null;

        System.out.println("Order cancelled!");
        return true;
    }

    public void showStatus(){
        System.out.println("Balance: " + payment.getBalance());
        System.out.println("MacBook: " + inventory.getStock("MacBook"));
        System.out.println("Lenovo Loq: "+inventory.getStock("Lenovo Loq"));
    }
}