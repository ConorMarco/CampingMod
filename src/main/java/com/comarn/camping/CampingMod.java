package com.comarn.camping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.comarn.camping.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CampingMod.MODID, name = CampingMod.MODNAME, version = CampingMod.VERSION, /*dependencies = "required-after:Forge@[11.16.0.1865,)",*/ useMetadata = true)
public class CampingMod {

    public static final String MODID = "camping";
    public static final String MODNAME = "Comarn's Camping";
    public static final String VERSION = "0.0.0.0";
    public static final String MCVERSION = "1.12.2";
    
    public static final Logger logger = LogManager.getLogger(MODID);

    public static CampingCreativeTab campingCreativeTab = new CampingCreativeTab(MODNAME);
    
    
    @SidedProxy(clientSide = "com.comarn.camping.proxy.ClientProxy", serverSide = "com.comarn.camping.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static CampingMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}

