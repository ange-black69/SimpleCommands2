package dries007.SimpleCommands;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import argo.jdom.JsonNode;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.TickRegistry;
import dries007.SimpleCommands.commands.*;
import dries007.SimpleCore.SimpleCore;
import dries007.SimpleCore.data;

@Mod(modid = "SimpleCommands", name= "SimpleCommands", version = "0", dependencies = "after:SimpleCore")
public class SimpleCommands 
{
	public static String PingMsg;
	public static String color;
	public static Boolean useBedAsHome;
	public static MinecraftServer server;
	public static NBTTagCompound worldData;
	public static Integer TPAtimeout = 0;
	@Instance("SimpleCommands")
	public SimpleCommands instance;
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{
		server = ModLoader.getMinecraftServerInstance();
		SimpleCore.server = server;
		ICommandManager commandManager = server.getCommandManager();
		ServerCommandManager serverCommandManager = ((ServerCommandManager) commandManager); 
		addCommands(serverCommandManager);
		worldData = data.loadData("worldData");
		
		TickRegistry.registerScheduledTickHandler(new TickHandler(), Side.SERVER);
	}
	
	@ServerStopping
	public void serverStopping(FMLServerStoppingEvent event)
	{
		data.saveData(worldData, "worldData");
	}
	
	public void addCommands(ServerCommandManager manager)
	{
		manager.registerCommand(new CommandPing());		
		manager.registerCommand(new CommandGm()); 		
		manager.registerCommand(new CommandDay());		
		manager.registerCommand(new CommandNight());	
		manager.registerCommand(new CommandRain());		
		manager.registerCommand(new CommandTellops());	
		manager.registerCommand(new CommandMilk());		
		manager.registerCommand(new CommandHeal());		
		manager.registerCommand(new CommandFly());		
		manager.registerCommand(new CommandGod());		
		manager.registerCommand(new CommandCi());		
		manager.registerCommand(new CommandBuff());		
		manager.registerCommand(new CommandBuffp());	
		manager.registerCommand(new CommandHome());		
		manager.registerCommand(new CommandSethome());	
		manager.registerCommand(new CommandCraft());	
		manager.registerCommand(new CommandChest());	
		manager.registerCommand(new CommandSpawn());
		//PW
		manager.registerCommand(new CommandPw()); 		
		manager.registerCommand(new CommandPwSet());	
		manager.registerCommand(new CommandPwDel());	
		//Warp
		manager.registerCommand(new CommandWarp());		
		manager.registerCommand(new CommandWarpSet());	
		manager.registerCommand(new CommandWarpDel());	
		//TP
		manager.registerCommand(new CommandTp());		
		manager.registerCommand(new CommandTpa());		
		//
		manager.registerCommand(new CommandTest());		//DEBUG
		
	}
	
	public static void tpToDim(EntityPlayer player, int dim)
	{
		ModLoader.getMinecraftServerInstance().getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player), dim);
	}
	
}
