import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderFacade facade = new OrderFacade();

        Customer customer = new Customer("C001", "john@gmail.com", "Astana");

        List<CartItem> cartItems = List.of(new CartItem("MacBook", 1, 2700), new CartItem("Lenovo Loq", 1, 600));

        System.out.println("Before order");
        facade.showStatus();
        String orderId = facade.placeOrder(cartItems, customer);
        System.out.println("After order");
        facade.showStatus();

        if (orderId != null) {
            facade.cancelOrder(orderId);
        }

        System.out.println("After cancellation");
        facade.showStatus();
    }
}