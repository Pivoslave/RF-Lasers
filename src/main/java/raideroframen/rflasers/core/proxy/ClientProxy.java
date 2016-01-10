package raideroframen.rflasers.core.proxy;

import raideroframen.rflasers.entity.EntityLaserBeam;
import raideroframen.rflasers.render.entity.RenderLaserBeam;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * @author RaiderofRamen
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserBeam.class, new RenderLaserBeam());
	}
}
