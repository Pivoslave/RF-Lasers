package raideroframen.rflasers.core;

import java.awt.Color;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraftforge.oredict.ShapedOreRecipe;
import raideroframen.rflasers.RFLasers;
import raideroframen.rflasers.api.item.ItemRFContainer;
import raideroframen.rflasers.block.BlockLenseImprinter;
import raideroframen.rflasers.entity.EntityLaserBeam;
import raideroframen.rflasers.item.ItemLaserCrystal;
import raideroframen.rflasers.item.ItemLense;
import raideroframen.rflasers.item.ItemMiningLaser;
import raideroframen.rflasers.tileentity.TileLenseImprinter;
import raideroframen.rflasers.util.ColorManager;
import raideroframen.rflasers.util.recipe.LenseImprinterRecipes;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author RaiderofRamen
 */
public class ModObjects
{
	public static BlockLenseImprinter lenseImprinter;
	
	public static ItemLaserCrystal laserCrystal;
    public static ItemMiningLaser miningLaser;
    public static ItemLense lense;
    
    public static void init(FMLInitializationEvent event)
    {
    	for(int index = 0; index < ItemDye.field_150921_b.length; index++)
    	{
    		ColorManager.put(index, ItemDye.field_150921_b[index], new Color(ItemDye.field_150922_c[index]));
    	}
    	
    	lenseImprinter = (BlockLenseImprinter) new BlockLenseImprinter().setBlockName("rflasers.lense_imprinter").setBlockTextureName("iron_block").setCreativeTab(ModObjects.tabRFLasers).setHardness(4.0F).setResistance(10.0F);
    	
        laserCrystal = (ItemLaserCrystal) new ItemLaserCrystal().setUnlocalizedName("rflasers.laser_crystal").setTextureName("rflasers:colored_crystal").setCreativeTab(ModObjects.tabRFLasers).setMaxStackSize(1).setHasSubtypes(true);
        miningLaser = (ItemMiningLaser) new ItemMiningLaser().setUnlocalizedName("rflasers.laser").setTextureName("rflasers:mining_laser").setCreativeTab(ModObjects.tabRFLasers).setMaxStackSize(1);
        lense = (ItemLense) new ItemLense().setUnlocalizedName("rflasers.lense").setTextureName("rflasers:lense").setCreativeTab(ModObjects.tabRFLasers).setMaxStackSize(1);
        
        GameRegistry.registerBlock(lenseImprinter, "lense_imprinter");
        
    	GameRegistry.registerItem(ModObjects.laserCrystal, "laser_crystal");
    	GameRegistry.registerItem(ModObjects.miningLaser, "mining_laser");
    	GameRegistry.registerItem(ModObjects.lense, "lense");
        
    	GameRegistry.registerTileEntity(TileLenseImprinter.class, "lense_imprinter");
        
        LenseImprinterRecipes.addLenseImprinterRecipe("Silk Touch", 1, "ingotGold", "gemEmerald", "ingotGold");
        LenseImprinterRecipes.addLenseImprinterRecipe("Fortune", 1, "ingotIron", "blockLapis", "ingotIron");
        LenseImprinterRecipes.addLenseImprinterRecipe("Fortune", 2, "ingotGold", "blockLapis", "ingotGold");
        LenseImprinterRecipes.addLenseImprinterRecipe("Fortune", 3, "gemDiamond", "blockLapis", "gemDiamond");
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModObjects.lenseImprinter), "iii", "ici", "iii", Character.valueOf('i'), "ingotIron", Character.valueOf('c'), "gemLaser"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModObjects.lense), " g ", "ggg", " g ", Character.valueOf('g'), "blockGlass"));
        
        for(int index = 0; index < ColorManager.getSize(); index++)
        {
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModObjects.laserCrystal, 1, index), " g ", "gdg", " g ", Character.valueOf('g'), "glowstone", Character.valueOf('d'), new ItemStack(Items.dye, 1, index)));
        	GameRegistry.addRecipe(new ShapedOreRecipe(ItemMiningLaser.getMiningLaser(ItemRFContainer.create(new ItemStack(ModObjects.miningLaser), 0, 100000, 2000, 1000), index), "di ", "ici", " li", Character.valueOf('d'), "gemDiamond", Character.valueOf('i'), "ingotIron", Character.valueOf('c'), new ItemStack(ModObjects.laserCrystal, 1, index), Character.valueOf('l'), Blocks.lever));
        }
        
        EntityRegistry.registerModEntity(EntityLaserBeam.class, "Laser Beam", 0, RFLasers.instance, 64, 10, true);
        NetworkRegistry.INSTANCE.registerGuiHandler(RFLasers.instance, new GuiHandler());
    }
    
    public static CreativeTabs tabRFLasers = new CreativeTabs("tabRFLasers")
    {
		@Override
		public Item getTabIconItem()
		{
			return ModObjects.laserCrystal;
		}
    };
    
    public static DamageSource causeLaserDamage(Entity hit, Entity shooter)
    {
    	return hit instanceof EntityPlayer ? new EntityDamageSourceIndirect("rflasers.laser.player", hit, shooter).setDamageBypassesArmor().setFireDamage().setProjectile() : new EntityDamageSourceIndirect("rfquarry.laser", hit, shooter).setDamageBypassesArmor().setFireDamage().setProjectile();
    }
}