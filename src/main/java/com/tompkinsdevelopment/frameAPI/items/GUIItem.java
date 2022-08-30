package com.tompkinsdevelopment.frameAPI.items;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GUIItem<T> implements Listener {

    private ItemStack itemStack;
    private T object;

    public GUIItem(T object)
    {

    }

    public T getObject()
    {
        return this.object;
    }

    public void configureMeta(String name, String... lore)
    {
        name = ChatColor.translateAlternateColorCodes('&', name);

        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        this.itemStack.setItemMeta(meta);
    }

    public void setDisplayName(String name)
    {
        name = ChatColor.translateAlternateColorCodes('&', name);

        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(name);
        this.itemStack.setItemMeta(meta);
    }

    public void setNameColor(ChatColor color)
    {
        setDisplayName(color + ChatColor.stripColor(this.itemStack.getItemMeta().getDisplayName()));
    }

    public String getDisplayName()
    {
        ItemMeta meta = this.itemStack.getItemMeta();
        if(meta == null) return this.itemStack.getType().name();

        return meta.getDisplayName();
    }

    public String getStrippedName()
    {
        return ChatColor.stripColor(getDisplayName());
    }

    public void setGlowing(boolean glowing)
    {
        if(glowing) {
            this.itemStack.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            this.itemStack.setItemMeta(meta);
        } else
        if(this.itemStack.containsEnchantment(Enchantment.MENDING)) {
            this.itemStack.removeEnchantment(Enchantment.MENDING);
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
            this.itemStack.setItemMeta(meta);

        }
    }

    public ItemStack getItemStack()
    {
        return this.itemStack;
    }
}
