package dries007.SimpleCommands.commands;

import dries007.SimpleCore.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandRain extends CommandBase
{
    public String getCommandName()
    {
        return "rain";
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
    	MinecraftServer.getServer().worldServers[0].setRainStrength(0);
        notifyAdmins(par1ICommandSender, "Rain turned off.", new Object[0]);
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return Permissions.hasPermission(par1ICommandSender.getCommandSenderName(), "SP."+getCommandName());
    }

}
