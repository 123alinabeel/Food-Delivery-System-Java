/**
 * A delivery option. New options can be added later by writing a new class
 * that implements this interface, without changing the Order class.
 */
public interface DeliveryOption {

    double getFee();
}
