import java.util.List;

public class FacadeTest {
    public static void main(String[] args) {
        Customer customer = new Customer("C001", "john@gmail.com", "Astana");
        List<CartItem> cart = List.of(new CartItem("MacBook", 1, 2700), new CartItem("Lenovo Loq", 1, 600));

        //test 1 Successful order
        OrderFacade facade = new OrderFacade();
        String orderId = facade.placeOrder(cart, customer);

        assert orderId != null : "Order should succeed";
        System.out.println("Test 1: Pass");

        //test 2 Cancel order
        boolean cancelled = facade.cancelOrder(orderId);
        assert cancelled : "Cancellation should succeed";
        System.out.println("Test 2 Pass");

        //test 3 Cancel again
        boolean cancelAgain = facade.cancelOrder(orderId);
        assert !cancelAgain : "Double cancellation should fail";
        System.out.println("Test 3: Pass");

        //test 4 Insufficient stock
        OrderFacade facade2 = new OrderFacade();
        List<CartItem> largeCart = List.of(new CartItem("MacBook", 999, 2700));
        String result2 = facade2.placeOrder(largeCart, customer);
        assert result2 == null: "Insufficient stock should fail";
        System.out.println("Test 4: Pass");

        //test 5 Payment declined
        OrderFacade facade3 = new OrderFacade();
        List<CartItem> expensiveCart = List.of(new CartItem("MacBook", 2, 2700));
        String result3 = facade3.placeOrder(expensiveCart, customer);
        assert result3 == null: "Payment should be declined";
        System.out.println("Test 5: Pass");

        //test 6 Shipping failed
        OrderFacade facade4 = new OrderFacade();
        Customer otherCustomer = new Customer("C002","test@gmail.com", "Almaty");
        String result4 = facade4.placeOrder(cart, otherCustomer);
        assert result4 == null: "Shipping should fail";
        System.out.println("Test 6: Pass");

        System.out.println("All test passed");
    }
}
