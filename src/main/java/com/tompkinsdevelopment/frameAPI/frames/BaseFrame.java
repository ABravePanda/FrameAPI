package com.tompkinsdevelopment.frameAPI.frames;

import com.tompkinsdevelopment.frameAPI.events.GUIItemClickEvent;
import com.tompkinsdevelopment.frameAPI.items.GUIItem;
import com.tompkinsdevelopment.frameAPI.modifiers.InputAllowed;
import com.tompkinsdevelopment.frameAPI.modifiers.OverrideEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseFrame implements Frame {

    protected JavaPlugin plugin;
    protected int id;
    protected int size;
    protected Player player;
    protected String title;
    protected GUIItem[] contents;
    protected Inventory inventory;
   // protected int usableSize = size - 9;
  //  protected int page = 1;
   // protected NavItems navItems;

    public BaseFrame(JavaPlugin plugin, int size)
    {
        this.plugin = plugin;
        this.size = size;
        this.contents = new GUIItem[size];
    }

    public BaseFrame(JavaPlugin plugin, int size, String title)
    {
        this.plugin = plugin;
        this.title = title;
        this.size = size;
        this.contents = new GUIItem[size];
    }

    public BaseFrame(JavaPlugin plugin, int size, Player player, String title)
    {
        this.plugin = plugin;
        this.player = player;
        this.title = title;
        this.size = size;
        this.contents = new GUIItem[size];
    }
    @Override
    public int getSize() {
        return inventory.getSize();
    }

    @Override
    public int getUsableSize() {
        return 0;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public GUIItem[] getContents() {
        return contents;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Inventory createInventory() {
        return Bukkit.createInventory(null, size, title);
    }

    @Override
    public void registerItems() {
        for(GUIItem item : contents)
            if(item != null)
            plugin.getServer().getPluginManager().registerEvents(item, plugin);
    }

    @Override
    public void registerFrame() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void generateFrame() {

    }

    @Override
    public void clearUsableItems() {

    }

    @Override
    public void setItem(int slot, GUIItem item) {
        if (slot >= contents.length || slot < 0)
            return;

        contents[slot] = item;
        getInventory().setItem(slot, item.getItemStack());
    }

    @Override
    public GUIItem getItem() {
        return null;
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {

        if (!e.getWhoClicked().equals(this.getPlayer()))
            return;

        if (e.getInventory() != this.getInventory())
            return;

        if(this.getClass().isAnnotationPresent(OverrideEvents.class))


        if (e.getClickedInventory() != this.getInventory()) {
            if(this.getClass().isAnnotationPresent(InputAllowed.class))
                e.setCancelled(true);
            return;
        }

        if (e.getCurrentItem() == null) {
            if(this.getClass().isAnnotationPresent(InputAllowed.class))
                e.setCancelled(true);
            return;
        }

        GUIItemClickEvent event = new GUIItemClickEvent(e, this);
        Bukkit.getPluginManager().callEvent(event);
        e.setCancelled(event.isCancelled());
    }
}
