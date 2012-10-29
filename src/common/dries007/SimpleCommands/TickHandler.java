package dries007.SimpleCommands;

import java.util.*;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ModLoader;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements IScheduledTickHandler
{
	public static List TPA = new ArrayList<EntityPlayer>();
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		//Does TPA timeout
		Iterator i = TPA.iterator();
		while(i.hasNext())
		{
			EntityPlayer player = (EntityPlayer) i.next();
			if(player.getEntityData().getString("TPA").equals("."))
			{
				player.getEntityData().setInteger("TPAtime", 0);
				TPA.remove(player);
			}
			int time = player.getEntityData().getInteger("TPAtime");
			if(time==0)
			{
				TPA.remove(player);
				try
				{
					EntityPlayer source = ModLoader.getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(player.getEntityData().getString("TPA"));
					source.addChatMessage("TPA timed out");
					player.addChatMessage("TPA timed out");
				}
				catch (Exception e)
				{
					FMLLog.severe(e.getMessage());
				}
			}
			else
			{
				player.getEntityData().setInteger("TPAtime", time-1);
			}
		}
		
		//Next!
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
		
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() 
	{
		return "SimpleCommands";
	}

	@Override
	public int nextTickSpacing() 
	{
		return 20;
	}
	
}
