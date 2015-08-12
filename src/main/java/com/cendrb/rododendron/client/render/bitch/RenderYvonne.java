package com.cendrb.rododendron.client.render.bitch;

import com.cendrb.rododendron.client.model.bitch.ModelYvonne;
import com.cendrb.rododendron.reference.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderYvonne extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/entity/yvonne.png");
	
	public RenderYvonne(ModelYvonne par1ModelBiped, float par2) {
		super(par1ModelBiped, par2);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return texture;
	}
}
