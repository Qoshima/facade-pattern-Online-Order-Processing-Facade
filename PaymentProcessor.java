public class PaymentProcessor {
    private double balance = 5000;

    public boolean processPayment(double amount) {
        if (amount > balance || amount <= 0) {
            System.out.println("Payment declined!");
            return false;
        }

        balance -= amount;
        System.out.println("Payment successful: " + amount);
        return true;
    }

    public void refundPayment(double amount) {
        balance += amount;
        System.out.println("Payment refunded: " + amount);
    }

    public double getBalance() {
        return balance;
    }
}