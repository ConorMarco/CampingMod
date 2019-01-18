package com.comarn.camping;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class CampingCreativeTab extends CreativeTabs {
  
	
  public CampingCreativeTab(String label) {
    super(label);
  }

  @SideOnly(Side.CLIENT)
  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(Items.BOOK);//TODO give creative tab an icon
  }

}