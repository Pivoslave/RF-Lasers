package raideroframen.rflasers.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import raideroframen.rflasers.RFLasers;
import raideroframen.rflasers.api.item.ItemRFContainer;
import raideroframen.rflasers.entity.EntityLaserBeam;
import raideroframen.rflasers.util.ColorManager;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * @author RaiderofRamen
 */
public class ItemMiningLaser extends ItemRFContainer
{
	public static ItemStack getMiningLaser(ItemStack itemstack, int colorID)
	{
		ItemStack value = itemstack.copy();
		
		value.getTagCompound().setInteger("Color", colorID);
		
		return value;
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack itemstack)
	{
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack itemstack)
	{
		return !itemstack.hasTagCompound() ? 0 : 1 - ((double) itemstack.getTagCompound().getInteger("Energy") / (double) itemstack.getTagCompound().getInteger("MaxEnergy"));
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean b1)
	{
		if(itemstack.hasTagCompound())
		{
			if(!Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.getKeyCode()))
			{
				list.add("Press " + Keyboard.getKeyName(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.getKeyCode()) + " to view more information.");
			}
			else
			{	
				if(itemstack.getTagCompound().hasKey("Energy") && itemstack.getTagCompound().hasKey("MaxEnergy"))
				{
					list.add(this.getEnergyStoredFormatted(itemstack));
				}
				
				if(itemstack.getTagCompound().hasKey("Color"))
				{
					list.add(ColorManager.getLocalizedName(itemstack.getTagCompound().getInteger("Color")) + " Beam");
				}
			}
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(!player.isSneaking())
		{
			if(this.getEnergyStored(itemstack) >= itemstack.getTagCompound().getInteger("MaxExtract"))
			{
				if(!world.isRemote)
				{
					world.spawnEntityInWorld(new EntityLaserBeam(world, player).getBeam(player, itemstack));
				}
					
				this.extractEnergy(itemstack, itemstack.getTagCompound().getInteger("MaxExtract"), false);
			}
		}
		else
		{
			player.openGui(RFLasers.instance, 1, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
		
		return itemstack;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for(int index = 0; index < 2; index++)
		{
			list.add(ItemMiningLaser.getMiningLaser(ItemRFContainer.create(new ItemStack(item), index * 100000, 100000, 2000, 1000), 1));
		}
	}
}
