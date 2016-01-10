package raideroframen.rflasers.render.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import raideroframen.rflasers.entity.EntityLaserBeam;
import raideroframen.rflasers.util.ColorManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author RaiderofRamen
 */
@SideOnly(Side.CLIENT)
public class RenderLaserBeam extends Render
{
	public static final ResourceLocation texture = new ResourceLocation("rflasers:textures/entities/laser_beam.png");

    public void doRender(EntityLaserBeam laser, double x, double y, double z, float yaw, float pitch)
    {
    	boolean isModAuthor = laser.getDataWatcher().getWatchableObjectString(2).equals("SoraRaion");
    	int color = laser.getDataWatcher().getWatchableObjectInt(3);
    	
    	this.bindTexture(texture);
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glRotatef(laser.prevRotationYaw + (laser.rotationYaw - laser.prevRotationYaw) * pitch - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(laser.prevRotationPitch + (laser.rotationPitch - laser.prevRotationPitch) * pitch, 0.0F, 0.0F, 1.0F);
        
        Tessellator tessellator = Tessellator.instance;
        
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.05625F, 0.05625F, 0.05625F);
        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
        
        tessellator.startDrawingQuads();
        
        if(isModAuthor)
        {
        	tessellator.setColorRGBA(Math.min(ColorManager.getColor(1).getRed(), 255), Math.min(ColorManager.getColor(1).getGreen(), 255), Math.min(ColorManager.getColor(1).getBlue(), 255), 255);
            
            tessellator.addVertex(-7.0D, 0.715D, -1.0D);
            tessellator.addVertex(7.0D, 0.715D, -1.0D);
            tessellator.addVertex(7.0D, 1.0D, -1.0D);
            tessellator.addVertex(-7.0D, 1.0D, -1.0D);
            
            tessellator.addVertex(-7.0D, 0.715D, 1.0D);
            tessellator.addVertex(7.0D, 0.715D, 1.0D);
            tessellator.addVertex(7.0D, 1.0D, 1.0D);
            tessellator.addVertex(-7.0D, 1.0D, 1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, 0.715D);
            tessellator.addVertex(7.0D, -1.0D, 0.715D);
            tessellator.addVertex(7.0D, -1.0D, 1.0D);
            tessellator.addVertex(-7.0D, -1.0D, 1.0D);
            
            tessellator.addVertex(-7.0D, 1.0D, 0.715D);
            tessellator.addVertex(7.0D, 1.0D, 0.715D);
            tessellator.addVertex(7.0D, 1.0D, 1.0D);
            tessellator.addVertex(-7.0D, 1.0D, 1.0D);
        	
            tessellator.setColorRGBA(Math.min(ColorManager.getColor(13).getRed(), 255), Math.min(ColorManager.getColor(13).getGreen(), 255), Math.min(ColorManager.getColor(13).getBlue(), 255), 255);
        	
        	tessellator.addVertex(-7.0D, 0.43D, -1.0D);
            tessellator.addVertex(7.0D, 0.43D, -1.0D);
            tessellator.addVertex(7.0D, 0.715D, -1.0D);
            tessellator.addVertex(-7.0D, 0.715D, -1.0D);
            
            tessellator.addVertex(-7.0D, 0.43D, 1.0D);
            tessellator.addVertex(7.0D, 0.43D, 1.0D);
            tessellator.addVertex(7.0D, 0.715D, 1.0D);
            tessellator.addVertex(-7.0D, 0.715D, 1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, 0.43D);
            tessellator.addVertex(7.0D, -1.0D, 0.43D);
            tessellator.addVertex(7.0D, -1.0D, 0.715D);
            tessellator.addVertex(-7.0D, -1.0D, 0.715D);
            
            tessellator.addVertex(-7.0D, 1.0D, 0.43D);
            tessellator.addVertex(7.0D, 1.0D, 0.43D);
            tessellator.addVertex(7.0D, 1.0D, 0.715D);
            tessellator.addVertex(-7.0D, 1.0D, 0.715D);
            
            tessellator.setColorRGBA(Math.min(ColorManager.getColor(11).getRed(), 255), Math.min(ColorManager.getColor(11).getGreen(), 255), Math.min(ColorManager.getColor(11).getBlue(), 255), 255);
        	
        	tessellator.addVertex(-7.0D, 0.145D, -1.0D);
            tessellator.addVertex(7.0D, 0.145D, -1.0D);
            tessellator.addVertex(7.0D, 0.43D, -1.0D);
            tessellator.addVertex(-7.0D, 0.43D, -1.0D);
            
            tessellator.addVertex(-7.0D, 0.145D, 1.0D);
            tessellator.addVertex(7.0D, 0.145D, 1.0D);
            tessellator.addVertex(7.0D, 0.43D, 1.0D);
            tessellator.addVertex(-7.0D, 0.43D, 1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, 0.145D);
            tessellator.addVertex(7.0D, -1.0D, 0.145D);
            tessellator.addVertex(7.0D, -1.0D, 0.43D);
            tessellator.addVertex(-7.0D, -1.0D, 0.43D);
            
            tessellator.addVertex(-7.0D, 1.0D, 0.145D);
            tessellator.addVertex(7.0D, 1.0D, 0.145D);
            tessellator.addVertex(7.0D, 1.0D, 0.43D);
            tessellator.addVertex(-7.0D, 1.0D, 0.43D);
            
            tessellator.setColorRGBA(Math.min(ColorManager.getColor(10).getRed(), 255), Math.min(ColorManager.getColor(10).getGreen(), 255), Math.min(ColorManager.getColor(10).getBlue(), 255), 255);
        	
        	tessellator.addVertex(-7.0D, -0.14D, -1.0D);
            tessellator.addVertex(7.0D, -0.14D, -1.0D);
            tessellator.addVertex(7.0D, 0.145D, -1.0D);
            tessellator.addVertex(-7.0D, 0.145D, -1.0D);
            
            tessellator.addVertex(-7.0D, -0.14D, 1.0D);
            tessellator.addVertex(7.0D, -0.14D, 1.0D);
            tessellator.addVertex(7.0D, 0.145D, 1.0D);
            tessellator.addVertex(-7.0D, 0.145D, 1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, -0.14D);
            tessellator.addVertex(7.0D, -1.0D, -0.14D);
            tessellator.addVertex(7.0D, -1.0D, 0.145D);
            tessellator.addVertex(-7.0D, -1.0D, 0.145D);
            
            tessellator.addVertex(-7.0D, 1.0D, -0.14D);
            tessellator.addVertex(7.0D, 1.0D, -0.14D);
            tessellator.addVertex(7.0D, 1.0D, 0.145D);
            tessellator.addVertex(-7.0D, 1.0D, 0.145D);
            
            tessellator.setColorRGBA(Math.min(ColorManager.getColor(12).getRed(), 255), Math.min(ColorManager.getColor(12).getGreen(), 255), Math.min(ColorManager.getColor(12).getBlue(), 255), 255);
        	
        	tessellator.addVertex(-7.0D, -0.425D, -1.0D);
            tessellator.addVertex(7.0D, -0.425D, -1.0D);
            tessellator.addVertex(7.0D, -0.14D, -1.0D);
            tessellator.addVertex(-7.0D, -0.14D, -1.0D);
            
            tessellator.addVertex(-7.0D, -0.425D, 1.0D);
            tessellator.addVertex(7.0D, -0.425D, 1.0D);
            tessellator.addVertex(7.0D, -0.14D, 1.0D);
            tessellator.addVertex(-7.0D, -0.14D, 1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, -0.425D);
            tessellator.addVertex(7.0D, -1.0D, -0.425D);
            tessellator.addVertex(7.0D, -1.0D, -0.14D);
            tessellator.addVertex(-7.0D, -1.0D, -0.14D);
            
            tessellator.addVertex(-7.0D, 1.0D, -0.425D);
            tessellator.addVertex(7.0D, 1.0D, -0.425D);
            tessellator.addVertex(7.0D, 1.0D, -0.14D);
            tessellator.addVertex(-7.0D, 1.0D, -0.14D);
            
            tessellator.setColorRGBA(Math.min(ColorManager.getColor(4).getRed(), 255), Math.min(ColorManager.getColor(4).getGreen(), 255), Math.min(ColorManager.getColor(4).getBlue(), 255), 255);
        	
        	tessellator.addVertex(-7.0D, -0.71D, -1.0D);
            tessellator.addVertex(7.0D, -0.71D, -1.0D);
            tessellator.addVertex(7.0D, -0.425D, -1.0D);
            tessellator.addVertex(-7.0D, -0.425D, -1.0D);
            
            tessellator.addVertex(-7.0D, -0.71D, 1.0D);
            tessellator.addVertex(7.0D, -0.71D, 1.0D);
            tessellator.addVertex(7.0D, -0.425D, 1.0D);
            tessellator.addVertex(-7.0D, -0.425D, 1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, -0.71D);
            tessellator.addVertex(7.0D, -1.0D, -0.71D);
            tessellator.addVertex(7.0D, -1.0D, -0.425D);
            tessellator.addVertex(-7.0D, -1.0D, -0.425D);
            
            tessellator.addVertex(-7.0D, 1.0D, -0.71D);
            tessellator.addVertex(7.0D, 1.0D, -0.71D);
            tessellator.addVertex(7.0D, 1.0D, -0.425D);
            tessellator.addVertex(-7.0D, 1.0D, -0.425D);
            
            tessellator.setColorRGBA(Math.min(ColorManager.getColor(5).getRed(), 255), Math.min(ColorManager.getColor(5).getGreen(), 255), Math.min(ColorManager.getColor(5).getBlue(), 255), 255);
        	
        	tessellator.addVertex(-7.0D, -1.0D, -1.0D);
            tessellator.addVertex(7.0D, -1.0D, -1.0D);
            tessellator.addVertex(7.0D, -0.71D, -1.0D);
            tessellator.addVertex(-7.0D, -0.71D, -1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, 1.0D);
            tessellator.addVertex(7.0D, -1.0D, 1.0D);
            tessellator.addVertex(7.0D, -0.71D, 1.0D);
            tessellator.addVertex(-7.0D, -0.71D, 1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, -1.0D);
            tessellator.addVertex(7.0D, -1.0D, -1.0D);
            tessellator.addVertex(7.0D, -1.0D, -0.71D);
            tessellator.addVertex(-7.0D, -1.0D, -0.71D);
            
            tessellator.addVertex(-7.0D, 1.0D, -1.0D);
            tessellator.addVertex(7.0D, 1.0D, -1.0D);
            tessellator.addVertex(7.0D, 1.0D, -0.71D);
            tessellator.addVertex(-7.0D, 1.0D, -0.71D);
            
            tessellator.setColorRGBA(Math.min(ColorManager.getColor(9).getRed(), 255), Math.min(ColorManager.getColor(9).getGreen(), 255), Math.min(ColorManager.getColor(9).getBlue(), 255), 255);
        	
        	tessellator.addVertex(-7.0D, -1.0D, -1.0D);
            tessellator.addVertex(-7.0D, -1.0D, 1.0D);
            tessellator.addVertex(-7.0D, 1.0D, 1.0D);
            tessellator.addVertex(-7.0D, 1.0D, -1.0D);
            
            tessellator.addVertex(7.0D, 1.0D, -1.0D);
            tessellator.addVertex(7.0D, 1.0D, 1.0D);
            tessellator.addVertex(7.0D, -1.0D, 1.0D);
            tessellator.addVertex(7.0D, -1.0D, -1.0D);
        }
        else
        {
        	tessellator.setColorRGBA(Math.min(ColorManager.getColor(color).getRed(), 255), Math.min(ColorManager.getColor(color).getGreen(), 255), Math.min(ColorManager.getColor(color).getBlue(), 255), 255);
        	
        	tessellator.addVertex(-7.0D, -1.0D, -1.0D);
            tessellator.addVertex(-7.0D, -1.0D, 1.0D);
            tessellator.addVertex(-7.0D, 1.0D, 1.0D);
            tessellator.addVertex(-7.0D, 1.0D, -1.0D);
            
            tessellator.addVertex(7.0D, 1.0D, -1.0D);
            tessellator.addVertex(7.0D, 1.0D, 1.0D);
            tessellator.addVertex(7.0D, -1.0D, 1.0D);
            tessellator.addVertex(7.0D, -1.0D, -1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, -1.0D);
            tessellator.addVertex(7.0D, -1.0D, -1.0D);
            tessellator.addVertex(7.0D, 1.0D, -1.0D);
            tessellator.addVertex(-7.0D, 1.0D, -1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, 1.0D);
            tessellator.addVertex(7.0D, -1.0D, 1.0D);
            tessellator.addVertex(7.0D, 1.0D, 1.0D);
            tessellator.addVertex(-7.0D, 1.0D, 1.0D);
            
            tessellator.addVertex(-7.0D, -1.0D, -1.0D);
            tessellator.addVertex(7.0D, -1.0D, -1.0D);
            tessellator.addVertex(7.0D, -1.0D, 1.0D);
            tessellator.addVertex(-7.0D, -1.0D, 1.0D);
            
            tessellator.addVertex(-7.0D, 1.0D, -1.0D);
            tessellator.addVertex(7.0D, 1.0D, -1.0D);
            tessellator.addVertex(7.0D, 1.0D, 1.0D);
            tessellator.addVertex(-7.0D, 1.0D, 1.0D);
        }
        
        tessellator.draw();
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public ResourceLocation getEntityTexture(EntityLaserBeam laser)
    {
        return texture;
    }
    
    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityLaserBeam) entity);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        this.doRender((EntityLaserBeam) entity, x, y, z, yaw, pitch);
    }
}
