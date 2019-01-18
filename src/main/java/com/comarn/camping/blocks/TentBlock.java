package com.comarn.camping.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.comarn.camping.CampingMod;
import com.comarn.camping.ModItems;
import com.google.common.base.Predicate;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TentBlock extends CampingBlock {
	
	static Predicate<EntityMob> isMonster = m -> (m.isCreatureType(EnumCreatureType.MONSTER, false));
	

	public TentBlock() {
		super("tentblock", Material.CLOTH, false);
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
		
		// Picking up tent
		ItemStack heldItem = playerIn.getHeldItem(hand);
		if (heldItem.isEmpty() && playerIn.isSneaking()) {
            //TODO
			//EntityItem dropped = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, stack);
            //worldIn.spawnEntity(dropped);
            
            //Break tent blocks
            removeTent(worldIn, pos, state);
            return true;
		}
		
		
		
		if(worldIn.isDaytime()) {
			playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
			return true;
		}
		
		List<EntityMob> monsters = worldIn.<EntityMob>getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB((double)pos.getX() - 8.0D, (double)pos.getY() - 5.0D, (double)pos.getZ() - 8.0D, (double)pos.getX() + 8.0D, (double)pos.getY() + 5.0D, (double)pos.getZ() + 8.0D), isMonster);
		if(!monsters.isEmpty()) {
			//TODO calc fire
			boolean fire = false;
			if(!fire) {
				playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);
				return true;
			}
		}
		
		// It's night and we can sleep
		worldIn.setWorldTime(0);
		CampingMod.logger.info("Slept in tent. Seting time to 0.");
		
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
        removeTent(worldIn, pos, state);
    }
	
	@Override public boolean canSilkHarvest() {
		return false;
	}
	
	@Override 
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune){
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		drops.add(new ItemStack(ModItems.tentItem, 1));
		return drops;
	}
	
	// Destroy blocks of tent without dropping items
	private void removeTent(World world, BlockPos pos, IBlockState state) {
		BlockPos horizOffset = BlockPos.ORIGIN.offset((EnumFacing)state.getValue(PROPERTY_FACING));
		
		BlockPos source = (state.getValue(PROPERTY_FRONT) ? pos : pos.subtract(horizOffset))
								.add(0, state.getValue(PROPERTY_TOP) ? -1 : 0, 0);
								
		for(int i=0; i<4; i++) {
			BlockPos h = i%2 == 0 ? source : source.add(horizOffset);
			BlockPos cur = h.add(0, (i & 2) >> 1, 0);
			if (world.getBlockState(cur).getBlock() instanceof TentBlock) {
				world.setBlockToAir(cur);
            }
		}
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
	//Todo make sure it drops items
	//Todo on block place
	//Todo on block break
	//Todo render
}
