package com.comarn.camping;

import com.comarn.camping.blocks.CampingBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder("camping:taconiteblock")
    public static CampingBlock taconiteBlock = new CampingBlock("taconiteblock", Material.ROCK);
	
    
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		 event.getRegistry().register(taconiteBlock);
	}
	
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(taconiteBlock).setRegistryName(taconiteBlock.getRegistryName()));
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		taconiteBlock.initModel();
	}
	
	@SideOnly(Side.CLIENT)
    public static void registerItemModels() {
//        bakedModelBlock.initItemModel();
    }
}
