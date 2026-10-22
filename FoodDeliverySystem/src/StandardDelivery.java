/**
 * Standard delivery costs $2.
 */
public class StandardDelivery implements DeliveryOption {

    private static final double FEE = 2.0;

    @Override
    public double getFee() {
        return FEE;
    }
}
