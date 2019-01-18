package com.comarn.camping;

import org.apache.logging.log4j.Level;

import com.comarn.camping.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;

public class CampingConfig {
    private static final String CATEGORY_GENERAL = "general";

    // This values below you can access elsewhere in your mod:
    public static boolean instantSleep = true;

    
    // Called from CommonProxy.preInit()
    public static void readConfig() {
//        Configuration cfg = CommonProxy.config;
//        try {
//            cfg.load();
//            initGeneralConfig(cfg);
//        } catch (Exception e1) {
//            CampingMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
//        } finally {
//            if (cfg.hasChanged()) {
//                cfg.save();
//            }
//        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        instantSleep = cfg.getBoolean("instantSleep", CATEGORY_GENERAL, instantSleep, 
        		"Determines whether instant sleep mode is used for tents. If true, right clicking tents will immediately "
        		+ "set the time to morning. If false, tents will work like normal beds, including setting spawnpoints!");
    }

}