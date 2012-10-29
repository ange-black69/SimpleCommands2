package dries007.SimpleCommands.commands;

import java.util.List;

import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandCi extends CommandBase
{
    public String getCommandName()
    {
        return "ci";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/ci <player>";
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
    		int var1;

            for (var1 = 0; var1 < target.inventory.mainInventory.length; ++var1)
            {
                if (target.inventory.mainInventory[var1] != null)
                {
                	target.inventory.player.dropPlayerItemWithRandomChoice(target.inventory.mainInventory[var1], true);
                	target.inventory.mainInventory[var1] = null;
                }
            }

            for (var1 = 0; var1 < target.inventory.armorInventory.length; ++var1)
            {
                if (target.inventory.armorInventory[var1] != null)
                {
                	target.inventory.player.dropPlayerItemWithRandomChoice(target.inventory.armorInventory[var1], true);
                	target.inventory.armorInventory[var1] = null;
                }
            }
    		target.addChatMessage("Your inventory has been cleaned!");
    		sender.sendChatToPlayer("You have cleaned " + target.username + "'s inventory.");
    	}
    	else
    	{
    		int var1;

            for (var1 = 0; var1 < target.inventory.mainInventory.length; ++var1)
            {
                if (target.inventory.mainInventory[var1] != null)
                {
                	target.inventory.player.dropPlayerItemWithRandomChoice(target.inventory.mainInventory[var1], true);
                	target.inventory.mainInventory[var1] = null;
                }
            }

            for (var1 = 0; var1 < target.inventory.armorInventory.length; ++var1)
            {
                if (target.inventory.armorInventory[var1] != null)
                {
                	target.inventory.player.dropPlayerItemWithRandomChoice(target.inventory.armorInventory[var1], true);
                	target.inventory.armorInventory[var1] = null;
                }
            }
    		target.addChatMessage("Your inventory has been cleaned!");
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
