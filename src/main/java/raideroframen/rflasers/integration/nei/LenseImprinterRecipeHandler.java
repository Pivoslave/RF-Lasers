package raideroframen.rflasers.integration.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import raideroframen.rflasers.core.ModObjects;
import raideroframen.rflasers.item.ItemLense;
import raideroframen.rflasers.util.recipe.LenseImprinterRecipes;
import raideroframen.rflasers.util.recipe.RecipeLenseImprinter;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

/**
 * @author RaiderofRamen
 */
public class LenseImprinterRecipeHandler extends TemplateRecipeHandler
{
	public class CachedLenseImprinterRecipe extends CachedRecipe
	{
		public List<PositionedStack> inputs = new ArrayList<PositionedStack>();
		public PositionedStack output;
		
		public CachedLenseImprinterRecipe(RecipeLenseImprinter recipe)
		{
			if(recipe == null)
			{
				return;
			}
			
			this.setIngredients(recipe.getInputs());
			inputs.add(new PositionedStack(new ItemStack(ModObjects.lense), 66, 36));
			output = new PositionedStack(recipe.getOutput(), 126, 36);
		}
		
		public void setIngredients(List<Object> inputs)
		{
			int index = 0;
			for(Object object : inputs)
			{
				if(object instanceof String)
				{
					this.inputs.add(new PositionedStack(OreDictionary.getOres((String) object), index == 0 || index == 2 ? 38 : index == 1 ? 26 : 144, index == 0 ? 12 : index == 2 ? 60 : 36));
				}
				else
				{
					this.inputs.add(new PositionedStack(object, index == 0 || index == 2 ? 38 : index == 1 ? 26 : 144, index == 0 ? 12 : index == 2 ? 60 : 36));
				}
				
				index++;
			}
		}
		
		@Override
		public List<PositionedStack> getIngredients()
		{
			return this.getCycledIngredients(cycleticks / 20, inputs);
		}
		
		@Override
		public PositionedStack getResult()
		{
			return output;
		}
	}
	
	@Override
	public int recipiesPerPage()
	{
		return 1;
	}
	
	@Override
	public void loadTransferRects()
	{
		transferRects.add(new RecipeTransferRect(new Rectangle(92, 36, 24, 18), "rflasers.lense_imprinter"));
	}
	
	@Override
	public void drawBackground(int recipe)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GuiDraw.changeTexture(this.getGuiTexture());
		GuiDraw.drawTexturedModalRect(4, 4, 4, 4, 144, 80);
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if(outputId.equals("rflasers.lense_imprinter"))
		{
			for(RecipeLenseImprinter recipe : LenseImprinterRecipes.recipes.values())
			{
				arecipes.add(new CachedLenseImprinterRecipe(recipe));
			}
		}
		else
		{
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		if(result.getItem() instanceof ItemLense)
		{
			for(RecipeLenseImprinter recipe : LenseImprinterRecipes.recipes.values())
			{
				if(recipe == null)
				{
					continue;
				}
				
				arecipes.add(new CachedLenseImprinterRecipe(recipe));
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		if(ingredient.getItem() instanceof ItemLense)
		{
			for(RecipeLenseImprinter recipe : LenseImprinterRecipes.recipes.values())
			{
				if(recipe == null)
				{
					continue;
				}
				
				if(recipe.getOutput() != null)
				{
					arecipes.add(new CachedLenseImprinterRecipe(recipe));
				}
			}
		}
		else for(RecipeLenseImprinter recipe : LenseImprinterRecipes.recipes.values())
		{
			if(recipe == null)
			{
				continue;
			}
			
			CachedLenseImprinterRecipe cachedRecipe = new CachedLenseImprinterRecipe(recipe);
			
			if(cachedRecipe.contains(cachedRecipe.inputs, ingredient))
			{
				arecipes.add(cachedRecipe);
			}
		}
	}
	
	@Override
	public String getGuiTexture()
	{
		return "rflasers:textures/gui/container/lense_imprinter.png";
	}
	
	@Override
	public String getRecipeName()
	{
		return StatCollector.translateToLocal("rflasers.nei.lense_imprinter");
	}
}