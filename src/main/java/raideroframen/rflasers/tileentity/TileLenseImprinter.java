package raideroframen.rflasers.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * @author RaiderofRamen
 */
public class TileLenseImprinter extends TileEntity implements IInventory
{
	protected ItemStack[] inventory = new ItemStack[5];
	
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
		return inventory[slot].splitStack(amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		inventory[slot] = itemstack;
	}

	@Override
	public String getInventoryName()
	{
		return "rflasers.laser_imprinter";
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
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet)
	{
		this.readFromNBT(packet.func_148857_g());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		NBTTagList tagList = compound.getTagList("Inventory", NBT.TAG_COMPOUND);
		for(int index = 0; index < tagList.tagCount(); index++)
		{
			if(tagList.getCompoundTagAt(index) != null)
			{
				NBTTagCompound tag = tagList.getCompoundTagAt(index);
				
				inventory[tag.getInteger("Slot")] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		NBTTagList tagList = new NBTTagList();
		for(int index = 0; index < inventory.length; index++)
		{
			if(inventory[index] != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				
				inventory[index].writeToNBT(tag);
				tag.setInteger("Slot", index);
				
				tagList.appendTag(tag);
			}
		}
		
		compound.setTag("Inventory", tagList);
	}
}