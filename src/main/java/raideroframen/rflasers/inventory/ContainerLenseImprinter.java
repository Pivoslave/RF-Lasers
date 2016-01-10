package raideroframen.rflasers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import raideroframen.rflasers.container.slot.SlotLenseImprinterInput;
import raideroframen.rflasers.container.slot.SlotLenseImprinterOutput;
import raideroframen.rflasers.item.ItemLense;
import raideroframen.rflasers.tileentity.TileLenseImprinter;
import raideroframen.rflasers.util.recipe.LenseImprinterRecipes;
import raideroframen.rflasers.util.recipe.RecipeLenseImprinter;

/**
 * @author RaiderofRamen
 */
public class ContainerLenseImprinter extends Container
{
	public TileLenseImprinter tile;
	
	public ContainerLenseImprinter(TileLenseImprinter tile, InventoryPlayer playerInventory)
	{
		this.tile = tile;
		
		this.addSlotToContainer(new Slot(tile, 0, 38, 12));
		this.addSlotToContainer(new Slot(tile, 1, 26, 36));
		this.addSlotToContainer(new Slot(tile, 2, 38, 60));
		
		this.addSlotToContainer(new SlotLenseImprinterInput(tile, 3, 66, 36));
		this.addSlotToContainer(new SlotLenseImprinterOutput(tile, 4, 126, 36));
		
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 9; column++)
			{
				this.addSlotToContainer(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 92 + row * 18));
			}
		}
		
		for(int column = 0; column < 9; column++)
		{
			this.addSlotToContainer(new Slot(playerInventory, column, 8 + column * 18, 150));
		}
	}
	
	public void attemptImprint()
	{
		if(tile.getStackInSlot(3) != null && tile.getStackInSlot(3).getItem() instanceof ItemLense)
		{
			for(RecipeLenseImprinter recipe : LenseImprinterRecipes.recipes.values())
			{
				if(recipe.matches(tile))
				{
					this.putStackInSlot(4, recipe.getOutput());
					
					this.putStackInSlot(0, null);
					this.putStackInSlot(1, null);
					this.putStackInSlot(2, null);
					this.putStackInSlot(3, null);
					
					break;
				}
			}
		}
	}
	
	@Override
	public void detectAndSendChanges()
	{	
		super.detectAndSendChanges();
		
		this.attemptImprint();
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

            if(index == 4)
            {
                if(!mergeItemStack(itemstack2, tile.getSizeInventory(), tile.getSizeInventory() + 36, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack2, itemstack);
            }
            else if(index != 3)
            {
            	boolean hasRecipe = false;
            	
                for(RecipeLenseImprinter recipe : LenseImprinterRecipes.recipes.values())
                {
                	if(recipe.matches(tile))
                	{
                		hasRecipe = true;
                	}
                }
                
                if(hasRecipe)
                {
                    if (!mergeItemStack(itemstack2, 0, 2, true))
                    {
                        return null;
                    }
                }
                else if(index >= tile.getSizeInventory() && index < tile.getSizeInventory() + 27)
                {
                    if(!mergeItemStack(itemstack2, tile.getSizeInventory() + 27, tile.getSizeInventory() + 36, false))
                    {
                        return null;
                    }
                }
                else if(index >= tile.getSizeInventory() + 27 && index < tile.getSizeInventory() + 36 && !mergeItemStack(itemstack2, tile.getSizeInventory() + 1, tile.getSizeInventory() + 27, false))
                {
                    return null;
                }
            }
            else if(!mergeItemStack(itemstack2, tile.getSizeInventory(), tile.getSizeInventory() + 36, false))
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