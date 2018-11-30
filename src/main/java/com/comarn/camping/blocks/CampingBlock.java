package com.comarn.camping.blocks;

import com.comarn.camping.CampingMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CampingBlock extends Block {
	public CampingBlock(String name, Material mat) {
        super(mat);
        setUnlocalizedName(CampingMod.MODID + "." + name);     // Used for localization (en_US.lang)
        setRegistryName(name);        // The unique name (within your mod) that identifies this block
        setCreativeTab(CampingMod.campingCreativeTab);
    }
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
