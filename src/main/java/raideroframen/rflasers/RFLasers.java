package raideroframen.rflasers;

import raideroframen.rflasers.core.ModObjects;
import raideroframen.rflasers.core.proxy.CommonProxy;
import raideroframen.rflasers.integration.nei.NEIRFLasersConfig;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

/**
 * @author RaiderofRamen
 */
@Mod(name = "RF Lasers", modid = "RFLasers", version = "2.0.0A1", dependencies = "required-after:CoFHCore")
public class RFLasers
{
    @Instance
    public static RFLasers instance;
	
    @SidedProxy(clientSide = "raideroframen.rflasers.core.proxy.ClientProxy", serverSide = "raideroframen.rflasers.core.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {	
    	ModObjects.init(event);
    	
    	if(Loader.isModLoaded("NotEnoughItems"))
    	{
    		new NEIRFLasersConfig().loadConfig();
    	}
    	
    	proxy.registerRenderers();
    }
}
