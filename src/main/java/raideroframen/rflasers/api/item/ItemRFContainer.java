package raideroframen.rflasers.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyContainerItem;

/**
 * @author RaiderofRamen
 */
public class ItemRFContainer extends Item implements IEnergyContainerItem
{	
	public static ItemStack create(ItemStack itemstack, int energy, int maxCapacity, int maxReceive, int maxExtract)
	{	
		if(!itemstack.hasTagCompound())
		{
			itemstack.setTagCompound(new NBTTagCompound());
		}
		
		itemstack.getTagCompound().setInteger("Energy", energy);
		itemstack.getTagCompound().setInteger("MaxEnergy", maxCapacity);
		itemstack.getTagCompound().setInteger("MaxReceive", maxReceive);
		itemstack.getTagCompound().setInteger("MaxExtract", maxExtract);
		
		return itemstack.copy();
	}
	
	public String getEnergyStoredFormatted(ItemStack itemstack)
	{
		int energy = itemstack.getTagCompound().getInteger("Energy");
		int maxEnergy = itemstack.getTagCompound().getInteger("MaxEnergy");
		
		String prefix = "kMGTPE";
		int energyLength = energy == 0 ? 0 : (int) (Math.log(energy) / Math.log(1000));
		int maxEnergyLength = (int) (Math.log(maxEnergy) / Math.log(1000));
		
		return String.format("%.1f%s / %.1f%s RF", energy / Math.pow(1000, energyLength), energy < 1000 ? "" : prefix.charAt(energyLength - 1), maxEnergy / Math.pow(1000, maxEnergyLength), prefix.charAt(maxEnergyLength - 1));
	}
	
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
	{
		if(!container.hasTagCompound())
		{
			container.setTagCompound(new NBTTagCompound());
		}
		
		int energy = container.getTagCompound().getInteger("Energy");
		int maxCapacity = container.getTagCompound().getInteger("MaxEnergy");
		int maxReceive2 = container.getTagCompound().getInteger("MaxReceive");
		int energyReceived = Math.min(maxCapacity - energy, Math.min(maxReceive2, maxReceive));

		if(!simulate)
		{
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
	{
		if(!container.hasTagCompound() || !container.getTagCompound().hasKey("Energy"))
		{
			return 0;
		}
		
		int energy = container.getTagCompound().getInteger("Energy");
		int maxExtract2 = container.getTagCompound().getInteger("MaxExtract");
		int energyExtracted = Math.min(energy, Math.min(maxExtract2, maxExtract));

		if(!simulate)
		{
			energy -= energyExtracted;
			container.getTagCompound().setInteger("Energy", energy);
		}
		
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container)
	{
		return container.hasTagCompound() && container.getTagCompound().hasKey("Energy") ? container.getTagCompound().getInteger("Energy") : 0;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return container.hasTagCompound() && container.getTagCompound().hasKey("MaxEnergy") ? container.getTagCompound().getInteger("MaxEnergy") : 0;
	}
}
