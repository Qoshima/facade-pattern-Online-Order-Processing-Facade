public class NotificationService {
    public void sendConfirmation() {
        System.out.println("Confirmation sent");
    }
    public void sendCancellation(String email) {
        System.out.println("Cancellation sent to " + email);
    }
}