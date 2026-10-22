/**
 * A meal. Final price = base price + $1.5 for each extra topping.
 */
public class Meal extends MenuItem {

    private static final double TOPPING_PRICE = 1.5;

    private int extraToppings;

    public Meal(String name, double basePrice, int extraToppings) {
        super(name, basePrice);
        this.extraToppings = extraToppings;
    }

    public int getExtraToppings() {
        return extraToppings;
    }

    @Override
    public double getFinalPrice() {
        return basePrice + TOPPING_PRICE * extraToppings;
    }
}
