package dries007.SimpleCommands.asm;

import java.io.File;
import java.util.Map;

import net.minecraft.src.Packet201PlayerInfo;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import dries007.SimpleCommands.SimpleCommands;
import dries007.SimpleCore.SimpleCore;
import dries007.SimpleCore.VanillaInterface;

public class SimpleCommandsPlugin implements IFMLLoadingPlugin, IFMLCallHook
{
	public static File myLocation;
	public static File config;

	@Override
	public String[] getLibraryRequestClass() 
	{	
		return null;
	}

	@Override
	public String[] getASMTransformerClass() 
	{
		return null;
	}

	@Override
	public String getModContainerClass() 
	{
		return "dries007.SimpleCommands.SimpleCommands";
	}

	@Override
	public String getSetupClass() 
	{
		return "dries007.SimpleCommands.asm.SimpleCommandsPlugin";
	}

	@Override
	public void injectData(Map<String, Object> data) 
	{
		if(data.containsKey("coremodLocation"))
		{
			myLocation = (File) data.get("coremodLocation");
			config = new File((File) data.get("mcLocation") + File.separator + "config", "SimpleCore.cfg");
		}
	}

	private void addOverrides() 
	{
		//SC doesn't need that
	}

	@Override
	public Void call() throws Exception 
	{
		addOverrides();
		config();
		return null;
	}
	
	public static void config()
	{
		final String CATEGORY_RANK = "Ranks";
		final String CATEGORY_OVERRIDE = "Override classes";
		
		Configuration configuration = new Configuration(SimpleCommandsPlugin.config);
		try
		{
			configuration.load();
			Property prop;
			prop = configuration.get(Configuration.CATEGORY_GENERAL, "useBedAsHome", false);
			prop.comment = "Use the last used bed as home";
			SimpleCommands.useBedAsHome = prop.getBoolean(false);
	    	
	    	prop = configuration.get(Configuration.CATEGORY_GENERAL, "PingMsg", "Pong!");
			prop.comment = "Response to the Ping Command";
			SimpleCommands.PingMsg = prop.value;

	    	prop = configuration.get(Configuration.CATEGORY_GENERAL, "TPAtimeout", 20);
	    	SimpleCommands.TPAtimeout = prop.getInt();
		} 
		catch (Exception e) 
		{
			System.out.println("SimpleCore has a problem loading it's configuration");
			System.out.println(e.getMessage());
		} 
		finally 
		{
			configuration.save();
		}
	}
}
