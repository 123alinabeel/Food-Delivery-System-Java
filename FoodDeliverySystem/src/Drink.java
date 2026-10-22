/**
 * A drink. Final price = base price, plus $1 if the drink is large.
 */
public class Drink extends MenuItem {

    private static final double LARGE_EXTRA = 1.0;

    private boolean large;

    public Drink(String name, double basePrice, boolean large) {
        super(name, basePrice);
        this.large = large;
    }

    public boolean isLarge() {
        return large;
    }

    @Override
    public double getFinalPrice() {
        if (large) {
            return basePrice + LARGE_EXTRA;
        }
        return basePrice;
    }
}
