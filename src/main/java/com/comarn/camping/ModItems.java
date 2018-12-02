package com.comarn.camping;


import com.comarn.camping.items.MarshmallowItem;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    @GameRegistry.ObjectHolder("camping:marshmallowitem")
    public static MarshmallowItem marshmallowItem;

	public static void registerItems(Register<Item> event) {
		event.getRegistry().register(new MarshmallowItem());
	}
}
