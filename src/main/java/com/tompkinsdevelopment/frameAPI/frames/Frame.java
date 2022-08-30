package com.tompkinsdevelopment.frameAPI.frames;

import org.bukkit.inventory.Inventory;

public interface Frame {

    int getSize();
    int getUsableSize();
    String getTitle();
    Inventory getInventory();
    Inventory createInventory();
    void registerItems();
    void registerFrame();
    void generateFrame();
    void clearUsableItems();


}
