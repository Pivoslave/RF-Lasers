package raideroframen.rflasers.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.input.Keyboard;

import raideroframen.rflasers.util.ColorManager;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * @author RaiderofRamen
 */
public class ItemLaserCrystal extends Item
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
			list.add(ColorManager.getLocalizedName(itemstack.getItemDamage()));
		}
    }
	
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {	
    	for(int index = 0; index < ColorManager.getSize(); index++)
    	{
    		list.add(new ItemStack(item, 1, index));
    		
    		OreDictionary.registerOre("gemLaser", new ItemStack(item, 1, index));
    	}
    }
}
