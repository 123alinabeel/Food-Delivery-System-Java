/**
 * Card payment: adds a 2% fee to the total.
 */
public class CardPayment implements PaymentMethod {

    private static final double FEE_RATE = 0.02;

    @Override
    public double calculateAmount(double total) {
        return total + total * FEE_RATE;
    }
}
