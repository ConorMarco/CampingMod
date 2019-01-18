package com.comarn.camping;


import com.comarn.camping.items.MarshmallowItem;
import com.comarn.camping.items.TentItem;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    @GameRegistry.ObjectHolder("camping:marshmallowitem")
    public static MarshmallowItem marshmallowItem;
    @GameRegistry.ObjectHolder("camping:tentitem")
    public static TentItem tentItem;

	public static void registerItems(Register<Item> event) {
		event.getRegistry().register(new MarshmallowItem());
		event.getRegistry().register(new TentItem());
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels(ModelRegistryEvent event) {
		marshmallowItem.initModel();
		tentItem.initModel();
    }
}
