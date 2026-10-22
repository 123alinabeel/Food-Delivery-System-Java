import java.util.ArrayList;
import java.util.List;

/**
 * The restaurant menu. It groups menu items but does not own them
 * (aggregation in the UML diagram).
 */
public class Menu {

    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
