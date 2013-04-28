package mekanism.generators.client;

import mekanism.api.EnumGas;
import mekanism.generators.common.TileEntityElectrolyticSeparator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderElectrolyticSeparator extends TileEntitySpecialRenderer
{
	private ModelElectrolyticSeparator model = new ModelElectrolyticSeparator();
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick)
	{
		renderAModelAt((TileEntityElectrolyticSeparator)tileEntity, x, y, z, partialTick);
	}
	
	private void renderAModelAt(TileEntityElectrolyticSeparator tileEntity, double x, double y, double z, float partialTick)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		bindTextureByName(tileEntity.outputType == EnumGas.HYDROGEN ? "/mods/mekanism/render/ElectrolyticSeparatorHydrogen.png" : 
			(tileEntity.outputType == EnumGas.OXYGEN ? "/mods/mekanism/render/ElectrolyticSeparatorOxygen.png" : "/mods/mekanism/render/ElectrolyticSeparatorNone.png"));
		
	    switch(tileEntity.facing)
	    {
		    case 2: GL11.glRotatef(270, 0.0F, 1.0F, 0.0F); break;
			case 3: GL11.glRotatef(90, 0.0F, 1.0F, 0.0F); break;
			case 4: GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); break;
			case 5: GL11.glRotatef(180, 0.0F, 1.0F, 0.0F); break;
	    }
		
	    GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
	    GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}
}
