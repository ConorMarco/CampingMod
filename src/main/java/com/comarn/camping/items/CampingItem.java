package com.comarn.camping.items;

import com.comarn.camping.CampingMod;

import net.minecraft.item.Item;

public class CampingItem extends Item {
	public CampingItem(String name) {
        setRegistryName(name);        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(CampingMod.MODID + name);     // Used for localization (en_US.lang)
        setCreativeTab(CampingMod.campingCreativeTab);
    }
}
