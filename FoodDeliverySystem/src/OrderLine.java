/**
 * One line of an order: a menu item and how many of it were ordered.
 */
public class OrderLine {

    private MenuItem item;
    private int quantity;

    public OrderLine(MenuItem item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    // item final price x quantity
    public double getLineTotal() {
        return item.getFinalPrice() * quantity;
    }
}
