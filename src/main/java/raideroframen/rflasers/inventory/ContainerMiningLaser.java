package raideroframen.rflasers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import raideroframen.rflasers.container.slot.SlotLense;

/**
 * @author RaiderofRamen
 */
public class ContainerMiningLaser extends Container
{
	public final InventoryMiningLaser inventory;
	
	public ContainerMiningLaser(InventoryMiningLaser inventory, InventoryPlayer playerInventory)
	{
		this.inventory = inventory;
		
		this.addSlotToContainer(new SlotLense(inventory, 0, 80, 32));
		
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 9; column++)
			{
				this.addSlotToContainer(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
			}
		}
		
		for(int column = 0; column < 9; column++)
		{
			this.addSlotToContainer(new Slot(playerInventory, column, 8 + column * 18, 142));
		}
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);
		
		if(!player.worldObj.isRemote)
		{
			inventory.writeToNBT(player.getHeldItem().getTagCompound());
		}
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();

            if(index == 0)
            {
                if(!mergeItemStack(itemstack2, inventory.getSizeInventory() + 1, inventory.getSizeInventory() + 37, false))
                {
                    return null;
                }

                slot.onSlotChange(itemstack2, itemstack);
            }
            else if(!mergeItemStack(itemstack2, 0, inventory.getSizeInventory(), false))
            {
                return null;
            }

            if(itemstack2.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if(itemstack2.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack2);
        }

        return itemstack;
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}