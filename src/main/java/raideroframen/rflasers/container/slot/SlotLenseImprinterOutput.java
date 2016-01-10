package raideroframen.rflasers.container.slot;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import raideroframen.rflasers.tileentity.TileLenseImprinter;

/**
 * @author RaiderofRamen
 */
public class SlotLenseImprinterOutput extends Slot
{
	public SlotLenseImprinterOutput(TileLenseImprinter tile, int index, int x, int y)
	{
		super(tile, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}
}