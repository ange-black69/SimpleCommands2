package dries007.SimpleCommands.commands;

import java.util.List;

import dries007.SimpleCommands.SimpleCommands;
import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandWarpSet extends CommandBase
{
	public CommandWarpSet()
	{
		//Permissions.addPermission("SP."+getCommandName());
	}
	
    public String getCommandName()
    {
        return "warpset";
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
    	EntityPlayer player = getCommandSenderAsPlayer(sender);
    	if (!warps.hasKey(args[0]))
    	{
    		NBTTagCompound warp = new NBTTagCompound(args[0]);
    		warp.setInteger("dim", player.dimension);
    		warp.setDouble("X", player.posX);
    		warp.setDouble("Y", player.posY);
    		warp.setDouble("Z", player.posZ);
    		warp.setFloat("rotP", player.rotationPitch);
    		warp.setFloat("rotY", player.rotationYaw);
    		warps.setCompoundTag(args[0], warp);
    		SimpleCommands.worldData.setCompoundTag("warps", warps);
    		sender.sendChatToPlayer("Warp made.");
    	}
    	else
    	{
    		sender.sendChatToPlayer("Warp already exists!");
    	}
    }
    
    public List addTabCompletionOptions(ICommandSender sender, String[] args)
    {
        return null;
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return Permissions.hasPermission(par1ICommandSender.getCommandSenderName(), "SP.warp.admin");
    }

}
