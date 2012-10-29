package dries007.SimpleCommands.commands;

import java.util.List;
import java.util.TimerTask;

import dries007.SimpleCommands.SimpleCommands;
import dries007.SimpleCommands.TickHandler;
import dries007.SimpleCore.*;
import java.util.Timer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandTpa extends CommandBase
{	
    public String getCommandName()
    {
        return "tpa";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() +" <player|a|d>";
    }

    public List getCommandAliases()
    {
        return null;
    }
    
    public void processCommand(ICommandSender sender, String[] args)
    {
    	if(args[0].equalsIgnoreCase("a"))
    	{
    		EntityPlayer player = getCommandSenderAsPlayer(sender);
    		EntityPlayer source = this.func_71540_a(player.getEntityData().getString("TPA"));
    		sender.sendChatToPlayer("TPa accepted.");
    		source.sendChatToPlayer("TPa accepted.");
    		player.getEntityData().setString("TPA", ".");
    		((EntityPlayerMP)source).playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationPitch, player.rotationYaw);
    	}
    	else if(args[0].equalsIgnoreCase("d"))
    	{
    		EntityPlayer player = getCommandSenderAsPlayer(sender);
    		EntityPlayer source = this.func_71540_a(player.getEntityData().getString("TPA"));
    		sender.sendChatToPlayer("TPa denied.");
    		source.sendChatToPlayer("TPa denied.");
    		player.getEntityData().setString("TPA", ".");
    	}
    	else
    	{
    		EntityPlayer target = args.length >= 1 ? this.func_71540_a(args[0]) : getCommandSenderAsPlayer(sender);
    		target.sendChatToPlayer(sender.getCommandSenderName() + " wants to TP to you. Use '/tpa a' to allow or '/tpa d' to deny.");
    		target.getEntityData().setString("TPA", sender.getCommandSenderName());
    		target.getEntityData().setInteger("TPAtime", SimpleCommands.TPAtimeout);
    		TickHandler.TPA.add(target);
    	}
    }

    protected EntityPlayer func_71540_a(String par1Str)
    {
        EntityPlayerMP var2 = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(par1Str);

        if (var2 == null)
        {
            throw new PlayerNotFoundException();
        }
        else
        {
            return var2;
        }
    }
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return Permissions.hasPermission(par1ICommandSender.getCommandSenderName(), "SP."+getCommandName());
    }
    
    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length != 1 ? null : getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
    }
    
}
