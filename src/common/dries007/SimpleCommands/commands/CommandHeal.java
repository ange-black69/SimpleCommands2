package dries007.SimpleCommands.commands;

import java.util.List;

import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EnumGameType;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.PlayerNotFoundException;
import net.minecraft.src.StatCollector;
import net.minecraft.src.WorldSettings;
import net.minecraft.src.WrongUsageException;

public class CommandHeal extends CommandBase
{
    public String getCommandName()
    {
        return "heal";
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
    		target.heal(9001);
    		target.getFoodStats().setFoodLevel(9001);
    		target.getFoodStats().setFoodSaturationLevel(9001);
    		target.addChatMessage("You been given GlaDOS' cake.");
    		sender.sendChatToPlayer("You have given " + target.username + " GlaDOS' cake.");
    	}
    	else
    	{
    		target.heal(9001);
    		target.getFoodStats().setFoodLevel(9001);
    		target.getFoodStats().setFoodSaturationLevel(9001);
    		target.addChatMessage("You have eaten GlaDOS' cake.");
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
