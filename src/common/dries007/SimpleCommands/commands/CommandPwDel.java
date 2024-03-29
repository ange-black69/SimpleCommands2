package dries007.SimpleCommands.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dries007.SimpleCommands.SimpleCommands;
import dries007.SimpleCore.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandPwDel extends CommandBase
{
	public CommandPwDel()
	{
		//Permissions.addPermission("SP."+getCommandName());
	}
	
    public String getCommandName()
    {
        return "pwdel";
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
    	NBTTagCompound data = Permissions.getPlayerSetting(sender.getCommandSenderName()).getCompoundTag("PW");
    	NBTTagCompound newData = new NBTTagCompound();
    	if (data.hasKey(args[0]))
    	{
    		Iterator pws = data.getTags().iterator();
    		while (pws.hasNext())
    		{
    			NBTTagString pw = (NBTTagString) pws.next();
    			if (!pw.getName().equalsIgnoreCase(args[0]))
    			{
    				newData.setString(pw.getName(), pw.data);
    			}
    			else
    			{
    				sender.sendChatToPlayer("PW deleted!");
    				return;
    			}
    		}
    		
    	}
    	else
    	{
    		sender.sendChatToPlayer("PW not found");
    	}
	}
    
    public List addTabCompletionOptions(ICommandSender sender, String[] args)
    {
    	if(args.length==1)
    	{
    		NBTTagCompound data = Permissions.getPlayerSetting(sender.getCommandSenderName()).getCompoundTag("PW");
        	List<String> pws = new ArrayList<String>();
        	Iterator i = data.getTags().iterator();
        	while(i.hasNext())
        	{
        		NBTBase tag = (NBTBase)i.next();
        		pws.add(tag.getName());
        	}
    		return getListOfStringsFromIterableMatchingLastWord(args, pws);
    	}
    	else
    	{
    		return null;
    	}
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return Permissions.hasPermission(par1ICommandSender.getCommandSenderName(), "SP.pw");
    }

}
