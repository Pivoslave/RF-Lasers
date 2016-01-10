package raideroframen.rflasers.container.slot;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import raideroframen.rflasers.item.ItemLense;
import raideroframen.rflasers.tileentity.TileLenseImprinter;

/**
 * @author RaiderofRamen
 */
public class SlotLenseImprinterInput extends Slot
{
	public SlotLenseImprinterInput(TileLenseImprinter tile, int index, int x, int y)
	{
		super(tile, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return itemstack.getItem() instanceof ItemLense;
	}
}