package raideroframen.rflasers.integration.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

/**
 * @author RaiderofRamen
 */
public class NEIRFLasersConfig implements IConfigureNEI
{
	@Override
	public String getName()
	{
		return "RF Lasers";
	}

	@Override
	public String getVersion()
	{
		return "2.0.0A1";
	}

	@Override
	public void loadConfig()
	{
		API.registerRecipeHandler(new LenseImprinterRecipeHandler());
        API.registerUsageHandler(new LenseImprinterRecipeHandler());
	}
}