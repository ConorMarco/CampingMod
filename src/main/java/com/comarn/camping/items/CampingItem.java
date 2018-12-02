package com.comarn.camping.items;

import com.comarn.camping.CampingMod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CampingItem extends Item {
	public CampingItem(String name) {
        setRegistryName(name);
        setUnlocalizedName(CampingMod.MODID + "." + name);
        setCreativeTab(CampingMod.campingCreativeTab);
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
