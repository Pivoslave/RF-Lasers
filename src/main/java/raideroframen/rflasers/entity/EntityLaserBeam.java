package raideroframen.rflasers.entity;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import raideroframen.rflasers.core.ModObjects;
import raideroframen.rflasers.inventory.InventoryMiningLaser;

/**
 * @author RaiderofRamen
 */
public class EntityLaserBeam extends EntityThrowable
{
    public ItemStack laser;
    
    public int mode;
    
    public EntityLaserBeam(World world)
    {
    	super(world);
    }
	
    public EntityLaserBeam(World world, EntityLivingBase shooter)
    {
    	super(world, shooter);
    	
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(shooter.posX, shooter.posY + (double) shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        
        this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        
        this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4F);
        this.motionY = (double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4F);
        this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4F);
        
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
    }
    
    public EntityLaserBeam getBeam(EntityLivingBase shooter, ItemStack itemstack)
    {
    	this.laser = itemstack;
    	
    	this.getDataWatcher().updateObject(2, shooter.getCommandSenderName());
    	this.getDataWatcher().updateObject(3, itemstack.getTagCompound().getInteger("Color"));
    	
    	this.getDataWatcher().setObjectWatched(2);
    	this.getDataWatcher().setObjectWatched(3);
    	
    	return this;
    }
    
    @Override
    public void entityInit()
    {
    	this.getDataWatcher().addObject(2, "");
    	this.getDataWatcher().addObject(3, 1);
    }
    
    @Override
    public float getGravityVelocity()
    {
    	return 0.0F;
    }
    
    @Override
	public void onImpact(MovingObjectPosition object)
	{
    	if(object.typeOfHit.equals(MovingObjectType.BLOCK))
    	{
    		if(this.isInWater())
    		{
    			this.setDead();
    		}
    		
    		if(this.worldObj.blockExists(object.blockX, object.blockY, object.blockZ) && this.worldObj.getBlock(object.blockX, object.blockY, object.blockZ).getHarvestLevel(this.worldObj.getBlockMetadata(object.blockX, object.blockY, object.blockZ)) <= 2);
    		{
    			if(!this.worldObj.isRemote)
    			{
    				InventoryMiningLaser inventory = new InventoryMiningLaser(laser);
    				
    				if(inventory != null && inventory.getStackInSlot(0) != null && inventory.getStackInSlot(0).hasTagCompound())
    				{
    					if(inventory.getStackInSlot(0).getTagCompound().getInteger("Silk Touch") > 0)
    					{
    						if(this.worldObj.getBlock(object.blockX, object.blockY, object.blockZ).canSilkHarvest(worldObj, (EntityPlayer) this.getThrower(), object.blockX, object.blockY, object.blockZ, worldObj.getBlockMetadata(object.blockX, object.blockY, object.blockZ)))
        					{
        						ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        			            ItemStack itemstack = new ItemStack(Item.getItemFromBlock(worldObj.getBlock(object.blockX, object.blockY, object.blockZ)), 1, worldObj.getBlockMetadata(object.blockX, object.blockY, object.blockZ));

        			            if(itemstack != null)
        			            {
        			                items.add(itemstack);
        			            }
        			            
        			            for(ItemStack itemstack2 : items)
        			            {
        			                worldObj.spawnEntityInWorld(new EntityItem(worldObj, object.blockX + 0.5, object.blockY + 0.5, object.blockZ + 0.5, itemstack2));
        			            }
        					}
    					}
    					else if(inventory.getStackInSlot(0).getTagCompound().getInteger("Fortune") > 0)
    					{
    						worldObj.getBlock(object.blockX, object.blockY, object.blockZ).dropBlockAsItem(worldObj, object.blockX, object.blockY, object.blockZ, worldObj.getBlockMetadata(object.blockX, object.blockY, object.blockZ), laser.getTagCompound().getInteger("Fortune"));
    					}
    					else
    					{
    						worldObj.getBlock(object.blockX, object.blockY, object.blockZ).dropBlockAsItem(worldObj, object.blockX, object.blockY, object.blockZ, worldObj.getBlockMetadata(object.blockX, object.blockY, object.blockZ), 0);
    					}
    				}
    				else
    				{
    					worldObj.getBlock(object.blockX, object.blockY, object.blockZ).dropBlockAsItem(worldObj, object.blockX, object.blockY, object.blockZ, worldObj.getBlockMetadata(object.blockX, object.blockY, object.blockZ), 0);
    				}
    				
    				this.worldObj.setBlockToAir(object.blockX, object.blockY, object.blockZ);
    			}
    		}
    	}
    	else if(object.typeOfHit.equals(MovingObjectType.ENTITY) && object.entityHit != this.getThrower())
    	{
    		if(object.entityHit != null)
    		{
    			object.entityHit.attackEntityFrom(ModObjects.causeLaserDamage(object.entityHit, this.getThrower()), 3.0F);
    		}
    	}
    	
    	this.setDead();
	}

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
    	
    	this.laser = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Laser"));
    	
    	this.getDataWatcher().updateObject(2, compound.getString("Shooter"));
    	this.getDataWatcher().updateObject(3, compound.getInteger("Color"));
    	
    	this.getDataWatcher().setObjectWatched(2);
    	this.getDataWatcher().setObjectWatched(3);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	
    	NBTTagCompound tag = new NBTTagCompound();
    	laser.writeToNBT(tag);
    	
    	compound.setTag("Laser", tag);
    	
    	compound.setString("Shooter", this.getDataWatcher().getWatchableObjectString(2));
    	compound.setInteger("Color", this.getDataWatcher().getWatchableObjectInt(3));
    }
}
