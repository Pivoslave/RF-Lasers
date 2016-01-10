package raideroframen.rflasers.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import raideroframen.rflasers.item.ItemLense;

/**
 * @author RaiderofRamen
 */
public class SlotLense extends Slot
{
	public SlotLense(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return itemstack.getItem() instanceof ItemLense;
	}
}