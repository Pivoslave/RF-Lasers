package raideroframen.rflasers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * @author RaiderofRamen
 */
public class InventoryMiningLaser implements IInventory
{
	protected ItemStack itemstack;
	protected ItemStack[] inventory;
	
	public InventoryMiningLaser(ItemStack itemstack)
	{
		this.itemstack = itemstack;
		this.inventory = new ItemStack[1];
		
		if(!itemstack.hasTagCompound())
		{
			itemstack.setTagCompound(new NBTTagCompound());
		}
		
		this.readFromNBT(itemstack.getTagCompound());
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack itemstack = this.getStackInSlot(slot);
		
		if(itemstack != null)
		{
			if(itemstack.stackSize > amount)
			{
				itemstack = itemstack.splitStack(amount);
			}
			else
			{
				this.setInventorySlotContents(slot, null);
			}
		}
		
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack itemstack = this.getStackInSlot(slot);
		this.setInventorySlotContents(0, null);
		
		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		inventory[slot] = itemstack;
		
		this.markDirty();
	}

	@Override
	public String getInventoryName()
	{
		return "rflasers.mining_laser";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		return true;
	}
	
	@Override
	public void markDirty() {}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	public void readFromNBT(NBTTagCompound compound)
	{
		NBTTagList tagList = compound.getTagList("Inventory", NBT.TAG_COMPOUND);
		
		for(int index = 0; index < tagList.tagCount(); index++)
		{
			if(tagList.getCompoundTagAt(index) != null)
			{
				inventory[tagList.getCompoundTagAt(index).getInteger("Slot")] = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(index));
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound compound)
	{
		NBTTagList tagList = new NBTTagList();
		
		for(int index = 0; index < this.getSizeInventory(); index++)
		{
			if(this.getStackInSlot(index) != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("Slot", index);
				this.getStackInSlot(index).writeToNBT(tag);
				
				tagList.appendTag(tag);
			}
		}
		
		compound.setTag("Inventory", tagList);
	}
}