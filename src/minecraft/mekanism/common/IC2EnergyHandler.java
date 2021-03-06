package mekanism.common;

import java.util.ArrayList;

import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import ic2.api.energy.event.EnergyTileSourceEvent;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergySource;

/**
 * This here is the core implementation of IC2 into Universal Cable.  Thanks to Player's hard work at making the EnergyNet
 * an event-based system, this is possible.
 * @author AidanBrady
 *
 */
public class IC2EnergyHandler 
{
	@ForgeSubscribe
	public void handleEnergy(EnergyTileSourceEvent event)
	{
		TileEntity tileEntity = (TileEntity)event.energyTile;
		ArrayList<TileEntity> ignoredTiles = new ArrayList<TileEntity>();
		
		for(ForgeDirection orientation : ForgeDirection.VALID_DIRECTIONS)
		{
			TileEntity tile = VectorHelper.getTileEntityFromSide(tileEntity.worldObj, new Vector3(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord), orientation);
			
			if(tile != null && tileEntity instanceof IEnergySource)
			{
				IEnergySource source = (IEnergySource)tileEntity;
				
				if(!source.emitsEnergyTo(tile, MekanismUtils.toIC2Direction(orientation.getOpposite())))
				{
					ignoredTiles.add(tile);
				}
			}
		}
		
		event.amount = (int)(CableUtils.emitEnergyFromAllSidesIgnore(event.amount*Mekanism.FROM_IC2, tileEntity, ignoredTiles)*Mekanism.TO_IC2);
	}
}
