/**
 * Tests the system with the professor's example:
 * 2 x Burger Meal (base $8, 1 extra topping) + 1 x Large Juice (base $3),
 * Express delivery, paid by card.
 */
public class Main {

    public static void main(String[] args) {

        // Menu items
        Meal burger = new Meal("Burger Meal", 8.0, 1);
        Drink juice = new Drink("Large Juice", 3.0, true);

        Menu menu = new Menu();
        menu.addItem(burger);
        menu.addItem(juice);

        // The professor's example
        DeliveryOption express = new ExpressDelivery();
        Order order = new Order("Ahmad", express);
        order.addItem(burger, 2);   // with a quantity
        order.addItem(juice);       // without a quantity -> 1

        System.out.println("Order #" + order.getOrderNumber() + " for " + order.getCustomerName());
        System.out.println(burger.getName() + " unit price: $" + String.format("%.2f", burger.getFinalPrice()));
        System.out.println(juice.getName() + " unit price: $" + String.format("%.2f", juice.getFinalPrice()));
        System.out.println("Subtotal:     $" + String.format("%.2f", order.getSubtotal()));
        System.out.println("Delivery fee: $" + String.format("%.2f", express.getFee()));
        System.out.println("Total:        $" + String.format("%.2f", order.getTotal()));

        // Invalid quantity and missing payment method are rejected; the order stays NEW
        try {
            order.addItem(juice, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Add with quantity 0 -> " + e.getMessage());
        }

        try {
            order.pay(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Pay without a method -> " + e.getMessage()
                    + " (status is still " + order.getStatus() + ")");
        }

        double amountPaid = order.pay(new CardPayment());
        System.out.println("Paid by card: $" + String.format("%.2f", amountPaid));
        System.out.println("Status: " + order.getStatus());

        // Status rules
        try {
            order.addItem(juice);
        } catch (IllegalStateException e) {
            System.out.println("Add item after payment -> " + e.getMessage());
        }

        try {
            order.cancel();
        } catch (IllegalStateException e) {
            System.out.println("Cancel a paid order -> " + e.getMessage());
        }

        order.deliver();
        System.out.println("Status: " + order.getStatus());

        // A second order: standard delivery, cash payment, cancel rules
        Order order2 = new Order("Sara", new StandardDelivery());
        order2.addItem(new Meal("Chicken Meal", 7.0, 0));
        System.out.println();
        System.out.println("Order #" + order2.getOrderNumber() + " for " + order2.getCustomerName());

        try {
            order2.deliver();
        } catch (IllegalStateException e) {
            System.out.println("Deliver an unpaid order -> " + e.getMessage());
        }

        System.out.println("Cash amount would be: $"
                + String.format("%.2f", new CashPayment().calculateAmount(order2.getTotal())));
        order2.cancel();
        System.out.println("Status: " + order2.getStatus());
    }
}
