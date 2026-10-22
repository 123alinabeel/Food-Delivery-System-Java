/**
 * Abstract parent class for every item on the menu.
 * Each kind of item (Meal, Drink) calculates its final price in its own way.
 */
public abstract class MenuItem {

    protected String name;
    protected double basePrice;

    public MenuItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    // Every subclass must say how its final price is calculated
    public abstract double getFinalPrice();
}
