package dries007.SimpleCommands.commands;

import java.util.List;

import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandFly extends CommandBase
{
    public String getCommandName()
    {
        return "fly";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() +" <player>";
    }

    public List getCommandAliases()
    {
        return null;
    }
    
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	EntityPlayer target = par2ArrayOfStr.length >= 1 ? this.func_71540_a(par2ArrayOfStr[0]) : getCommandSenderAsPlayer(sender);
    	
    	if (target != sender)
    	{
    		if(target.capabilities.allowFlying)
    		{
    			target.capabilities.allowFlying = false;
    		}
    		else
    		{
    			target.capabilities.allowFlying = true;
    			target.addChatMessage("You've been given wings.");
        		sender.sendChatToPlayer("You have given " + target.username + " wings.");
    		}
    	}
    	else
    	{
    		if(target.capabilities.allowFlying)
    		{
    			target.capabilities.allowFlying = false;
    			target.addChatMessage("Your wings have been taken.");
    		}
    		else
    		{
    			target.capabilities.allowFlying = true;
    			target.addChatMessage("You've got wings.");
    		}
    		
    	}
    	((EntityPlayerMP)target).playerNetServerHandler.sendPacketToPlayer(new Packet202PlayerAbilities(target.capabilities));
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
