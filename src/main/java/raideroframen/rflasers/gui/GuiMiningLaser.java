package raideroframen.rflasers.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import raideroframen.rflasers.inventory.ContainerMiningLaser;
import raideroframen.rflasers.inventory.InventoryMiningLaser;
import cofh.core.gui.GuiBaseAdv;
import cofh.core.gui.element.TabInfo;

/**
 * @author RaiderofRamen
 */
public class GuiMiningLaser extends GuiBaseAdv
{
	public static ResourceLocation background = new ResourceLocation("rflasers:textures/gui/container/mining_laser.png");
	
	public GuiMiningLaser(InventoryMiningLaser inventory, InventoryPlayer playerInventory)
	{
		super(new ContainerMiningLaser(inventory, playerInventory), background);
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.addTab(new TabInfo(this, StatCollector.translateToLocal("inventory.rflasers.mining_laser.info")));
	}
}