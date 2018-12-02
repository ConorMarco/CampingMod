package com.comarn.camping;

import com.comarn.camping.blocks.CampfireBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

//    @GameRegistry.ObjectHolder("camping:taconiteblock")
//    public static CampfireBlock campfireBlock = new CampfireBlock();
    
//    public static CampingBlock boringBlock = new CampingBlock("boringblock", Material.ROCK);
	
    
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
//		 event.getRegistry().register(campfireBlock);
	}
	
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
//        event.getRegistry().register(new ItemBlock(campfireBlock).setRegistryName(campfireBlock.getRegistryName()));
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void initModels(ModelRegistryEvent event) {
//		campfireBlock.initModel();
	}
	
	@SideOnly(Side.CLIENT)
    public static void initItemModels() {
//        bakedModelBlock.initItemModel();
    }
}
