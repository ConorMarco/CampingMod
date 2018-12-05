package com.comarn.camping;

import com.comarn.camping.blocks.TentBlock;

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
	
	
    @GameRegistry.ObjectHolder("camping:tentblock")
    public static TentBlock tentBlock = new TentBlock();
	
    
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(tentBlock);
	}
	
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(tentBlock).setRegistryName(tentBlock.getRegistryName()));
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void initModels(ModelRegistryEvent event) {
		tentBlock.initModel();
	}
	
	@SideOnly(Side.CLIENT)
    public static void initItemModels() {
//        bakedModelBlock.initItemModel();
    }
}
