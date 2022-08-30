package com.tompkinsdevelopment.frameAPI;

import com.tompkinsdevelopment.frameAPI.frames.BaseFrame;
import com.tompkinsdevelopment.frameAPI.frames.Frame;
import org.bukkit.plugin.java.JavaPlugin;

public class FrameAPI {

    private JavaPlugin plugin;

    public FrameAPI(JavaPlugin plugin)
    {
        this.plugin = plugin;
    }

    public void registerFrame(Frame frame)
    {
        getPlugin().getServer().getPluginManager().registerEvents(frame, plugin);
    }

    public Frame createFrame()
    {
        return new BaseFrame(getPlugin(), 24);
    }

    private JavaPlugin getPlugin()
    {
        if(plugin == null) return null;
        return plugin;
    }

}
