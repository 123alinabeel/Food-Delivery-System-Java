/**
 * A way of paying. It turns the order total into the amount actually charged.
 */
public interface PaymentMethod {

    double calculateAmount(double total);
}
