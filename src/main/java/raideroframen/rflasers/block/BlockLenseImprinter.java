package raideroframen.rflasers.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import raideroframen.rflasers.RFLasers;
import raideroframen.rflasers.tileentity.TileLenseImprinter;

/**
 * @author RaiderofRamen
 */
public class BlockLenseImprinter extends Block implements ITileEntityProvider
{
	public BlockLenseImprinter()
	{
		super(Material.iron);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float posX, float posY, float posZ)
	{	
		if(world.getTileEntity(x, y, z) != null)
		{
			player.openGui(RFLasers.instance, 0, world, x, y, z);
		}
		
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileLenseImprinter();
	}
}