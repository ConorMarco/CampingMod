package com.comarn.camping.proxy;

import com.comarn.camping.ModBlocks;
import com.comarn.camping.ModItems;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
        ModBlocks.registerItemModels();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModBlocks.registerModels(event);
// TODO        ModItems.registerModels(event);
    }
}