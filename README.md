# Online Order Processing Facade (Option B)
## Description
This project demonstrates the Facade Structural Design Pattern in Java.

OrderFacade simplifies order processing by coordinating four independent subsystems:

- InventoryService - reserves and releases products.
- PaymentProcessor - processes payments and refunds.
- ShippingService - handles shipping and cancellation.
- NotificationService - sends notifications.

The client interacts only with OrderFacade. Subsystems do not depend on the Facade and can be used independently.

## How it works?
The client uses two main methods

`facade.placeOrder(cartItems, customer);`  
`facade.cancelOrder(orderId);`  

Order flow: Inventory -> Payment -> Shipping -> Notification.  

If a step fails, the Facade performs rollback by releasing reserved stock and refunding payment when necessary.  

The implementation supports one active order at a time.

## Testing
Six scenarios are tested using Java assertions:
1. Successful Order
2. Order Cancellation
3. Repeated Cancellation
4. Insufficient Stock
5. Payment Declined
6. Shipping Failure

Run tests with assertions enabled:

`javac *.java`  
`java -ea FacadeTest`

## Clean Code Principles
1. Meaningful Names
```java
placeOrder(cartItems, customer);
cancelOrder(orderId);
```
Method names clearly describe their purpose.
2. Single Responsibility Principle
```java
inventory.reserveStock(productId, quantity);
payment.processPayment(total);
```
Each subsystem has one responsibility. The Facade coordinates operations without implementing their internal logic.
3. Encapsulation
```java
private InventoryService inventory = new InventoryService();
```
Subsystem references are private. The client does not access them through the Facade.
4. Small and Focused Methods.
```java
public void refundPayment(double amount) {
    balance += amount;
}
```
Each method performs a specific task, making the code easier to understand and maintain.
5. Proper Error Handling
```java
if (!payment.processPayment(total)) {
    for (CartItem cartItem : reserved) {
        inventory.releaseStock(item.getProductId(), item.getQuantity());
        }
    return null;
}
```
If payment fails, the Facade releases reserved products to keep inventory consistent.