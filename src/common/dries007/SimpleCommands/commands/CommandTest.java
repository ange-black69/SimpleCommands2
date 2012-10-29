package dries007.SimpleCommands.commands;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import dries007.SimpleCommands.Crafting;
import dries007.SimpleCore.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class CommandTest extends CommandBase
{
    public String getCommandName()
    {
        return "test";
    }

    public void processCommand(ICommandSender sender, String[] args)
    {
    	EntityPlayerMP player = ((EntityPlayerMP) sender);
    	player.sendChatToPlayer("This is a debug commands for testing purposes only.");
    	
    	player.theItemInWorldManager.setBlockReachDistance(0D);
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return true;
    }

}
