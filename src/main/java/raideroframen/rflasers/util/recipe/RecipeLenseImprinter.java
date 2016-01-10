package raideroframen.rflasers.util.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import raideroframen.rflasers.item.ItemLense;
import raideroframen.rflasers.tileentity.TileLenseImprinter;

/**
 * @author RaiderofRamen
 */
public class RecipeLenseImprinter
{
	ItemStack output;
	List<Object> inputs;
	
	public RecipeLenseImprinter(ItemStack output, Object... inputs)
	{
		this.output = output;
		
		List<Object> inputsToAdd = new ArrayList<Object>();
		for(Object object : inputs)
		{
			if(object instanceof String || object instanceof ItemStack)
			{
				inputsToAdd.add(object);
			}
			else
			{
				throw new IllegalArgumentException("Invalid input");
			}
		}
		
		this.inputs = inputsToAdd;
	}
	
	public boolean matches(TileLenseImprinter tile)
	{
		List<Object> missingInputs = new ArrayList<Object>(inputs);
		
		for(int index = 0; index < tile.getSizeInventory(); index++)
		{
			ItemStack itemstack = tile.getStackInSlot(index);
			
			if(itemstack == null)
			{
				break;
			}
			
			if(itemstack.getItem() instanceof ItemLense)
			{
				continue;
			}
			
			int itemstackIndex = -1, oreDictionaryIndex = -1;
			
			for(int missingIndex = 0; missingIndex < missingInputs.size(); missingIndex++)
			{
				Object input = missingInputs.get(missingIndex);
				
				if(input instanceof String)
				{
					List<ItemStack> valid = OreDictionary.getOres((String) input);
					boolean found = false;
					
					for(ItemStack oreDictionaryItemstack : valid)
					{
						ItemStack copy = oreDictionaryItemstack.copy();
						
						if(copy.getItemDamage() == Short.MAX_VALUE)
						{
							copy.setItemDamage(itemstack.getItemDamage());
						}
						
						if(itemstack.isItemEqual(copy))
						{
							oreDictionaryIndex = missingIndex;
							found = true;
							break;
						}
					}
					
					if(found)
					{
						break;
					}
				}
				else if(input instanceof ItemStack && (((ItemStack) input).getItem() == itemstack.getItem() && ((ItemStack) input).getItemDamage() == itemstack.getItemDamage()))
				{
					itemstackIndex = missingIndex;
				}
			}
			
			if(itemstackIndex != -1)
			{
				missingInputs.remove(itemstackIndex);
			}
			else if(oreDictionaryIndex != -1)
			{
				missingInputs.remove(oreDictionaryIndex);
			}
			else
			{
				return false;
			}
		}
		
		return missingInputs.isEmpty();
	}
	
	public List<Object> getInputs()
	{
		return new ArrayList<Object>(inputs);
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
}