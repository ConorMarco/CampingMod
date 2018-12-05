package com.comarn.camping.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TentBlock extends CampingBlock {

	public TentBlock() {
		super("tentblock", Material.CLOTH);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
        return false;
    }
	
	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
	@Override
    public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity player) {
        return true;
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) {
			return true;
		}
		
		// Sleeping code taken from BlockBed
		EntityPlayer.SleepResult entityplayer$sleepresult = playerIn.trySleep(pos);
		
		if (entityplayer$sleepresult == EntityPlayer.SleepResult.OK)
        {
            worldIn.setBlockState(pos, state, 4);
        }
        else
        {
            if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW)
            {
                playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
            }
            else if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_SAFE)
            {
            	//Todo if nearby campfire
                playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);
            }
            else if (entityplayer$sleepresult == EntityPlayer.SleepResult.TOO_FAR_AWAY)
            {
                playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.tooFarAway", new Object[0]), true);
            }
        }
		
		return true;
	}
	
	
	
	// Begin Blockstate Stuff ----------------------------------------------------------------------------------------------
	public static final PropertyDirection PROPERTY_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool PROPERTY_TOP = PropertyBool.create("top");
	public static final PropertyBool PROPERTY_FRONT = PropertyBool.create("front");
	
	// This property is not used for anything, and only necessary for bed code to work properly.
	public static final PropertyBool PROPERTY_OCCUPIED = PropertyBool.create("occupied");
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PROPERTY_FACING, PROPERTY_TOP, PROPERTY_FRONT, PROPERTY_OCCUPIED);
	}
	
	
	// Meta : Top (1 bit), Front (1 bit), Facing (2 bits)
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = state.getValue(PROPERTY_FACING).getHorizontalIndex();
		meta |= (state.getValue(PROPERTY_FRONT) ? 0x04 : 0);
		meta |= (state.getValue(PROPERTY_TOP) ? 0x08 : 0);
		return meta;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		boolean front = (meta & 0x04) != 0;
	    boolean top = (meta & 0x08) != 0;
	    return this.getDefaultState().withProperty(PROPERTY_FACING, facing).withProperty(PROPERTY_FRONT, front)
	    		.withProperty(PROPERTY_TOP, top).withProperty(PROPERTY_OCCUPIED, false);
	}
	
	//Todo register block
	//Todo make easy to break
	//Todo on block place
	//Todo on block break
	//Todo render
	//Todo on right click
	//Todo make not set spawn
}
