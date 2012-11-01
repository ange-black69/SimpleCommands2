package dries007.SimpleCommands.commands;

import java.util.List;

import dries007.SimpleCommands.SimpleCommands;
import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandPing extends CommandBase
{
	public CommandPing()
	{
		//Permissions.addPermission("SP."+getCommandName());
	}
	
    public String getCommandName()
    {
        return "ping";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName();
    }

    public List getCommandAliases()
    {
        return null;
    }
    
    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
    	par1ICommandSender.sendChatToPlayer(SimpleCommands.PingMsg);
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return true;
    }

}
