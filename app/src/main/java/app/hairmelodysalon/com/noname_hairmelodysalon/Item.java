package app.hairmelodysalon.com.noname_hairmelodysalon;

public class Item {
    private String id;
    private String name;
    private String category;
    private int stock;

    public Item(String id, String name, String category, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}