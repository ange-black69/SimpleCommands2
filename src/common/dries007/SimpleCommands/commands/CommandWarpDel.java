package dries007.SimpleCommands.commands;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dries007.SimpleCommands.SimpleCommands;
import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandWarpDel extends CommandBase
{
    public String getCommandName()
    {
        return "warpdel";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <name>";
    }

    public List getCommandAliases()
    {
        return null;
    }
    
    public void processCommand(ICommandSender sender, String[] args)
    {
    	if (args.length!=1) throw new WrongUsageException("/" + getCommandName() + " <name>");
    	NBTTagCompound warps = SimpleCommands.worldData.getCompoundTag("warps");
    	if (warps.hasKey(args[0]))
    	{
    		NBTTagCompound newWarps = new NBTTagCompound();
    		Iterator pws = warps.getTags().iterator();
    		while (pws.hasNext())
    		{
    			NBTTagCompound pw = (NBTTagCompound) pws.next();
    			if (!pw.getName().equalsIgnoreCase(args[0]))
    			{
    				newWarps.setCompoundTag(pw.getName(), pw);
    			}
    			else
    			{
    				sender.sendChatToPlayer("Warp deleted!");
    				SimpleCommands.worldData.setCompoundTag("warps", warps);
    				return;
    			}
    		}
    	}
    	else
    	{
    		sender.sendChatToPlayer("Warp not found");
    	}
	}
    
    public List addTabCompletionOptions(ICommandSender sender, String[] args)
    {
    	NBTTagCompound warps = SimpleCommands.worldData.getCompoundTag("warps");
    	return (List) warps.getTags();
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return Permissions.hasPermission(par1ICommandSender.getCommandSenderName(), "SP.warpmod");
    }

}
