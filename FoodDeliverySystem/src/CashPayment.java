/**
 * Cash payment: no extra fee, the customer pays the total.
 */
public class CashPayment implements PaymentMethod {

    @Override
    public double calculateAmount(double total) {
        return total;
    }
}
