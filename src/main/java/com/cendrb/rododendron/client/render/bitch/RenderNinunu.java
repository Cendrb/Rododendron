package com.cendrb.rododendron.client.render.bitch;

import com.cendrb.rododendron.reference.Reference;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderNinunu extends RenderBiped {

	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/entity/ninunu.png");

	public RenderNinunu(ModelBiped par1ModelBiped, float par2) {
		super(par1ModelBiped, par2);
		
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return texture;
	}

}
