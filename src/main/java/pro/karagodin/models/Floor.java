package pro.karagodin.models;

public class Floor {
    protected Item item;

    public Item getItem() {
        return item;
    }

    /** remove item from floor and return it */
    public Item pickItem() {
        var pickedItem = this.item;
        this.item = null;
        return pickedItem;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
