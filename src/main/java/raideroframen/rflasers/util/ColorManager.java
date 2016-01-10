package raideroframen.rflasers.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.StatCollector;

/**
 * @author RaiderofRamen
 */
public class ColorManager
{
	private static Map<Integer, String> registry = new HashMap<Integer, String>();
	private static Map<String, Color> container = new HashMap<String, Color>();
	
	public static void remove(int id)
	{	
		if(registry.containsKey(id) && container.containsKey(registry.get(id)))
		{
			container.remove(registry.get(id));
			registry.remove(id);
		}
		else
		{
			throw new IllegalArgumentException("No ID or Name found.");
		}
	}
	
	public static void put(int id, String name, Color color)
	{	
		if(!(registry.containsKey(id) || container.containsKey(name)))
		{
			registry.put(id, "color." + name + ".name");
			container.put("color." + name + ".name", color);
		}
		else
		{
			throw new IllegalArgumentException("ID or Name already in use.");
		}
	}
	
	public static Color getColor(int id)
	{
		return container.get(registry.get(id));
	}
	
	public static Color getColor(String name)
	{
		return container.get(name);
	}
	
	public static int getSize()
	{
		return Math.min(registry.size(), container.size());
	}
	
	public static String getLocalizedName(int id)
	{
		return StatCollector.translateToLocal(registry.get(id));
	}
	
	public static String getLocalizedName(String name)
	{
		return StatCollector.translateToLocal("color_" + name + ".name");
	}
}