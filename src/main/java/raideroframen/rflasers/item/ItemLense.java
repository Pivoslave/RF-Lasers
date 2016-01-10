package raideroframen.rflasers.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.Constants.NBT;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * @author RaiderofRamen
 */
public class ItemLense extends Item
{	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean b1)
	{	
		if(!Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.getKeyCode()))
		{
			list.add("Press " + Keyboard.getKeyName(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.getKeyCode()) + " to view more information.");
		}
		else
		{	
			if(itemstack.hasTagCompound())
			{
				for(Object key : itemstack.getTagCompound().func_150296_c())
				{
					if(itemstack.getTagCompound().func_150299_b((String) key) == NBT.TAG_INT)
					{
						list.add(key + " " + itemstack.getTagCompound().getInteger((String) key));
					}
				}
			}
			else
			{
				list.add("Blank");
			}
		}
	}
}