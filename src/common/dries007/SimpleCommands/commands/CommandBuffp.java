package dries007.SimpleCommands.commands;

import java.util.List;

import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandBuffp extends CommandBase
{
    public String getCommandName()
    {
        return "buffp";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <player> <ID> [Duration] [Strength]";
    }

    public List getCommandAliases()
    {
        return null;
    }
    
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	EntityPlayer player = func_71540_a(par2ArrayOfStr[0]);
    	try
    	{
    		int ID = 0;
    		int dur = 15 * 20;
    		int amp = 0;
    		switch (par2ArrayOfStr.length) 
    		{
    		case 4:
    			amp = Integer.valueOf(par2ArrayOfStr[3]);
    		case 3:
    			dur = Integer.valueOf(par2ArrayOfStr[2]) * 20;
    		case 2:
    			ID = Integer.valueOf(par2ArrayOfStr[1]);
    			break;
    		default:
    			buffhelp(player);
    		}
    		if (ID!=0)
    		{
    			EntityPlayer target = (EntityPlayer) sender;
    			PotionEffect eff = new PotionEffect(ID, dur, amp);
    			target.addPotionEffect(eff);
    		}
    	}
    	catch (Exception e)
    	{
    		throw new WrongUsageException("/" + getCommandName() + " <player> <ID> [Duration] [Strength]");
    	}
    }

    public void buffhelp(EntityPlayer player)
    {
    	player.addChatMessage("ID    =>Name");
		player.addChatMessage("1     =>Speed");
		player.addChatMessage("2     =>Slow");
		player.addChatMessage("3     =>Haste");
		player.addChatMessage("4     =>Fatigue");
		player.addChatMessage("5     =>Strength");
		player.addChatMessage("6     =>Heal");
		player.addChatMessage("7     =>Damage");
		player.addChatMessage("8     =>Jump");
		player.addChatMessage("9     =>Nausea");
		player.addChatMessage("10    =>Regen");
		player.addChatMessage("11    =>Resistance");
		player.addChatMessage("12    =>Fire Resistance");
		player.addChatMessage("13    =>Waterbreathing");
		player.addChatMessage("14    =>Invisibility");
		player.addChatMessage("15    =>Blindness");
		player.addChatMessage("16    =>Nightvision");
		player.addChatMessage("17    =>Hunger");
		player.addChatMessage("18    =>Weakness");
		player.addChatMessage("19    =>Poison");
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
