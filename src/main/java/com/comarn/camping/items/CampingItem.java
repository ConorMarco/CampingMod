package com.comarn.camping.items;

import com.comarn.camping.CampingMod;

import net.minecraft.item.Item;

public class CampingItem extends Item {
	public CampingItem(String name) {
        setRegistryName(name);
        setUnlocalizedName(CampingMod.MODID + "." + name);
        setCreativeTab(CampingMod.campingCreativeTab);
    }
}
