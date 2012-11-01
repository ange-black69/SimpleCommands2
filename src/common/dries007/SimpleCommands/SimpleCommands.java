package dries007.SimpleCommands;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

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
import dries007.SimpleCore.*;

public class SimpleCommands extends DummyModContainer 
{
	public static String PingMsg;
	public static String color;
	public static Boolean useBedAsHome;
	public static MinecraftServer server;
	public static NBTTagCompound worldData;
	public static Integer TPAtimeout = 0;
	
	public SimpleCommands()
	{
		super(new ModMetadata());
		ModMetadata meta	=	getMetadata();
        meta.modId      	=	"SimpleCommands";
        meta.name       	=	"SimpleCommands";
        meta.version    	=	"0.1";
        meta.authorList 	=	Arrays.asList("Dries007");
        meta.credits		=	"Dries007, ChickenBones for making his mods open-source!";
        meta.description	=	"Adds a bunch of commands.";
        meta.url        	=	"http://ssm.dries007.net";
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}
	
	@Subscribe
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
	
	@Subscribe
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
		manager.registerCommand(new CommandLag());
		manager.registerCommand(new CommandItems());
		manager.registerCommand(new CommandMobs());
		manager.registerCommand(new CommandKit());
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
