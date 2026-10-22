import java.util.ArrayList;
import java.util.List;

/**
 * A customer order. It keeps the order lines, the delivery option and the status,
 * calculates the subtotal and total, and enforces the status rules.
 */
public class Order {

    // Shared by all orders, so each new order gets the next number (1001, 1002, ...)
    private static int nextOrderNumber = 1001;

    private int orderNumber;
    private String customerName;
    private OrderStatus status = OrderStatus.NEW;
    private DeliveryOption delivery;
    private List<OrderLine> lines = new ArrayList<>();
    private PaymentMethod paymentMethod;   // stays null until the order is paid

    public Order(String customerName, DeliveryOption delivery) {
        this.orderNumber = generateOrderNumber();
        this.customerName = customerName;
        this.delivery = delivery;
    }

    private static int generateOrderNumber() {
        int number = nextOrderNumber;
        nextOrderNumber++;
        return number;
    }

    // Add one item (quantity = 1)
    public void addItem(MenuItem item) {
        addItem(item, 1);
    }

    // Add an item with a specific quantity (only while the order is NEW)
    public void addItem(MenuItem item, int quantity) {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Items can only be added while the order is NEW.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        lines.add(new OrderLine(item, quantity));
    }

    // Subtotal = sum of (item price x quantity)
    public double getSubtotal() {
        double subtotal = 0;
        for (OrderLine line : lines) {
            subtotal += line.getLineTotal();
        }
        return subtotal;
    }

    // Total = subtotal + delivery fee (the payment fee is NOT included here)
    public double getTotal() {
        return getSubtotal() + delivery.getFee();
    }

    // Pays the order and returns the amount charged by the payment method
    public double pay(PaymentMethod method) {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Only a NEW order can be paid.");
        }
        if (method == null) {
            throw new IllegalArgumentException("A payment method is required.");
        }

        // Calculate the amount first; the status changes only if this succeeds
        double amount = method.calculateAmount(getTotal());

        this.paymentMethod = method;
        status = OrderStatus.PAID;
        return amount;
    }

    public void deliver() {
        if (status != OrderStatus.PAID) {
            throw new IllegalStateException("Only a PAID order can be delivered.");
        }
        status = OrderStatus.DELIVERED;
    }

    public void cancel() {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Only a NEW order can be cancelled.");
        }
        status = OrderStatus.CANCELLED;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
