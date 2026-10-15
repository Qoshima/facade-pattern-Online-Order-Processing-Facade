public class OrderFacade {
    private InventoryService inventory = new InventoryService();
    private PaymentProcessor payment = new PaymentProcessor();
    private ShippingService shipping = new ShippingService();
    private NotificationService notification = new NotificationService();

    public void placeOrder() {
        inventory.reserveStock();
        payment.processPayment();
        shipping.shipOrder();
        notification.sendConfirmation();
    }
}