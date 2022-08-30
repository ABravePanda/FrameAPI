package com.tompkinsdevelopment.frameAPI.frames;

import com.tompkinsdevelopment.frameAPI.items.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public interface Frame extends Listener {

    int getSize();
    int getUsableSize();
    String getTitle();
    Inventory getInventory();
    Inventory createInventory();
    Player getPlayer();
    GUIItem[] getContents();
    void registerItems();
    void registerFrame();
    void generateFrame();
    void clearUsableItems();
    void setItem(int slot, GUIItem item);

    GUIItem getItem();



}
