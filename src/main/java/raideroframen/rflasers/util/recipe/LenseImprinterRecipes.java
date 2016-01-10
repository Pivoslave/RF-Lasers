package raideroframen.rflasers.util.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import raideroframen.rflasers.core.ModObjects;

/**
 * @author RaiderofRamen
 */
public class LenseImprinterRecipes
{
	public static Map<String, RecipeLenseImprinter> recipes = new HashMap<String, RecipeLenseImprinter>();
	
	public static void addLenseImprinterRecipe(String key, int value, Object... inputs)
	{
		ItemStack itemstack = new ItemStack(ModObjects.lense);
		
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setInteger(key, value);
		
		recipes.put(key + " " + value, new RecipeLenseImprinter(itemstack, inputs));
	}
}