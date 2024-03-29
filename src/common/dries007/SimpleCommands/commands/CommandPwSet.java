package dries007.SimpleCommands.commands;

import java.util.List;

import dries007.SimpleCommands.SimpleCommands;
import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandPwSet extends CommandBase
{
	public CommandPwSet()
	{
		//Permissions.addPermission("SP."+getCommandName());
	}
	
    public String getCommandName()
    {
        return "pwset";
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
    	NBTTagCompound data = Permissions.getPlayerSetting(sender.getCommandSenderName());
    	NBTTagCompound PW = data.getCompoundTag("PW");
    	EntityPlayer player = getCommandSenderAsPlayer(sender);
    	if (!PW.hasKey(args[0]))
    	{
    		Double X = player.posX;
    		Double Y = player.posY;
    		Double Z = player.posZ;
    		Float pitch = player.rotationPitch;
    		Float yaw = player.rotationYaw;
    		PW.setString(args[0], X + ";" + Y + ";" + Z + ";" + pitch + ";" + yaw + ";" + player.dimension);
    		data.setCompoundTag("PW", PW);
    		Permissions.setPlayerSetting(sender.getCommandSenderName(), data);
    		sender.sendChatToPlayer("PW set!");
    	}
    	else
    	{
    		sender.sendChatToPlayer("PW already exists!");
    	}
    }
    
    public List addTabCompletionOptions(ICommandSender sender, String[] args)
    {
        return null;
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return Permissions.hasPermission(par1ICommandSender.getCommandSenderName(), "SP.pw");
    }

}
