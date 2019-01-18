package com.comarn.camping.proxy;

import java.io.File;

import com.comarn.camping.CampingConfig;
import com.comarn.camping.ModBlocks;
import com.comarn.camping.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
	public static Configuration config;
	
    public void preInit(FMLPreInitializationEvent e) {
    	File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "modtut.cfg"));
        CampingConfig.readConfig();
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	ModBlocks.registerBlocks(event);
    	
//    	GameRegistry.registerTileEntity(CampTIle.class, new ResourceLocation("mre:tileslave"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	ModBlocks.registerItemBlocks(event);
        
        ModItems.registerItems(event);
    }
}
