/**
 * Express delivery costs $5.
 */
public class ExpressDelivery implements DeliveryOption {

    private static final double FEE = 5.0;

    @Override
    public double getFee() {
        return FEE;
    }
}
