package dries007.SimpleCommands.commands;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import dries007.SimpleCommands.Crafting;
import dries007.SimpleCommands.SimpleCommands;
import dries007.SimpleCore.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandKit extends CommandBase
{
    public String getCommandName()
    {
        return "kit";
    }

    public String getCommandUsage(ICommandSender sender)
    {
    	if(Permissions.hasPermission(sender.getCommandSenderName(), "SP.kit.admin"))
    	{
    		return "/items <name> [save|delete]";
    	}
    	else
    	{
    		return "/items <name>";
    	}
    }
    
    public void processCommand(ICommandSender sender, String[] args)
    {
    	EntityPlayerMP player = ((EntityPlayerMP) sender);
    	// End user
    	if(args.length==1)
    	{
    		NBTTagCompound kits = SimpleCommands.worldData.getCompoundTag("kits");
    		if(!kits.hasKey(args[0])) sender.sendChatToPlayer("That kit doesn't exist!");
    		else
    		{
    			ItemStack stack = new ItemStack(1,1,0);
    			NBTTagCompound kit = kits.getCompoundTag(args[0]);
    			for(Object item : kit.getTags())
    			{
    				try
    				{
    					NBTTagCompound nbtItem = (NBTTagCompound) item;
    					stack.readFromNBT(nbtItem);
    					player.inventory.addItemStackToInventory(stack);
    				}
    				catch(Exception e) {sender.sendChatToPlayer("Problem!");}
    			}
    			sender.sendChatToPlayer("The kit " + kit.getName() + " has been added to your inventory.");
    		}
    		return;
    	}
    	// Settigns
    	if(args.length==2)
    	{
    		if(args[1].equalsIgnoreCase("save"))
    		{
    			NBTTagCompound kits = SimpleCommands.worldData.getCompoundTag("kits");
        		if(kits.hasKey(args[0])) sender.sendChatToPlayer("That kit already exist!");
        		else
        		{
        			int i = 0;
        			NBTTagCompound kit = kits.getCompoundTag(args[0]);
        			for(ItemStack itemstack : player.inventory.mainInventory)
        			{
        				if(itemstack!=null)
        				{
        					kit.setCompoundTag(""+i, itemstack.writeToNBT(new NBTTagCompound()));
        					i++;
        				}
        			}
        			for(ItemStack itemstack : player.inventory.armorInventory)
        			{
        				if(itemstack!=null)
        				{
        					kit.setCompoundTag(""+i, itemstack.writeToNBT(new NBTTagCompound()));
        					i++;
        				}
        			}
        			kits.setCompoundTag(args[0].toLowerCase(), kit);
        			SimpleCommands.worldData.setCompoundTag("kits", kits);
        			sender.sendChatToPlayer("Your inventory (inc. armor!) has been saved as kit '" + args[0].toLowerCase() + "'");
        		}
    		}
    		else if(args[1].equalsIgnoreCase("delete"))
    		{
    			NBTTagCompound kits = SimpleCommands.worldData.getCompoundTag("kits");
    			if(!kits.hasKey(args[0])) sender.sendChatToPlayer("That kit doesn't exist!");
    			else
    			{
    				NBTTagCompound newKitList = new NBTTagCompound();
    				for(Object item : kits.getTags())
        			{
        				try
        				{
        					NBTTagCompound nbtTag = (NBTTagCompound) item;
        					if(nbtTag.getName().equalsIgnoreCase(args[0]))
        					{
        						sender.sendChatToPlayer("Kit '" + nbtTag.getName() + "' has been removed");
        					}
        					else
        					{
        						newKitList.setCompoundTag(nbtTag.getName(), nbtTag);
        					}
        				}
        				catch(Exception e) {sender.sendChatToPlayer("Problem!");}
        				SimpleCommands.worldData.setCompoundTag("kits", newKitList);
						return;
        			}
    			}
    		}
    		else throw new WrongUsageException(getCommandUsage(sender));
    	}
    	else throw new WrongUsageException(getCommandUsage(sender));
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
    	return Permissions.hasPermission(sender.getCommandSenderName(), "SP."+getCommandName());
    }
}
