public class InventoryService {
    private int lenovos = 100;
    private int macbooks = 200;

    public boolean reserveStock(String productId, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        if (productId.equals("Lenovo Loq")) {
            if (lenovos >= quantity) {
                lenovos -= quantity;
                System.out.println("Reserved " + quantity + " Lenovo Loq");
                return true;
            }
        }
        if (productId.equals("MacBook")) {
            if (macbooks >= quantity) {
                macbooks -= quantity;
                System.out.println("Reserved " + quantity + " MacBook");
                return true;
            }
        }
        return false;
    }

    public void releaseStock(String productId, int quantity) {
        if (quantity <= 0) {
            return;
        }
        if (productId.equals("Lenovo Loq")) {
            lenovos += quantity;
        }
        if (productId.equals("MacBook")) {
            macbooks += quantity;
        }
        System.out.println("Released " + quantity + " " + productId);
    }

    public int getStock(String productId) {
        if (productId.equals("Lenovo Loq")) {
            return lenovos;
        }
        if (productId.equals("MacBook")) {
            return macbooks;
        }
        return 0;
    }
}
