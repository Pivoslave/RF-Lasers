package raideroframen.rflasers.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import raideroframen.rflasers.inventory.ContainerLenseImprinter;
import raideroframen.rflasers.tileentity.TileLenseImprinter;
import cofh.core.gui.GuiBaseAdv;
import cofh.core.gui.element.TabInfo;
import cofh.lib.util.helpers.StringHelper;

/**
 * @author RaiderofRamen
 */
public class GuiLenseImprinter extends GuiBaseAdv
{
	public static ResourceLocation background = new ResourceLocation("rflasers:textures/gui/container/lense_imprinter.png");
	
	public GuiLenseImprinter(TileLenseImprinter tile, InventoryPlayer playerInventory)
	{
		super(new ContainerLenseImprinter(tile, playerInventory), background);
		
		this.ySize += 8;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.addTab(new TabInfo(this, StringHelper.localize("inventory.rflasers.lense_imprinter.info")));
	}
}