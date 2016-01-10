package raideroframen.rflasers.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import raideroframen.rflasers.gui.GuiLenseImprinter;
import raideroframen.rflasers.gui.GuiMiningLaser;
import raideroframen.rflasers.inventory.ContainerLenseImprinter;
import raideroframen.rflasers.inventory.ContainerMiningLaser;
import raideroframen.rflasers.inventory.InventoryMiningLaser;
import raideroframen.rflasers.tileentity.TileLenseImprinter;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * @author RaiderofRamen
 */
public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == 0)
		{
			return new ContainerLenseImprinter((TileLenseImprinter) world.getTileEntity(x, y, z), player.inventory);
		}
		else if(ID == 1)
		{
			return new ContainerMiningLaser(new InventoryMiningLaser(player.getHeldItem()), player.inventory);
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == 0)
		{
			return new GuiLenseImprinter((TileLenseImprinter) world.getTileEntity(x, y, z), player.inventory);
		}
		else if(ID == 1)
		{
			return new GuiMiningLaser(new InventoryMiningLaser(player.getHeldItem()), player.inventory);
		}
		else
		{
			return null;
		}
	}
}