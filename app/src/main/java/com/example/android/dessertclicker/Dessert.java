package com.example.android.dessertclicker;

/**
 * Simple data class that represents a dessert. Includes the resource id integer associated with
 * the image, the price it's sold for, and the startProductionAmount, which determines when
 * the dessert starts to be produced.
 */
public class Dessert {

    public int getImageId() {
        return imageId;
    }

    public int getPrice() {
        return price;
    }

    public int getStartProductionAmount() {
        return startProductionAmount;
    }

    private final int imageId;
    private final int price;
    private final int startProductionAmount;

    public Dessert(int imageId, int price, int startProductionAmount) {
        this.imageId = imageId;
        this.price = price;
        this.startProductionAmount = startProductionAmount;
    }
}
