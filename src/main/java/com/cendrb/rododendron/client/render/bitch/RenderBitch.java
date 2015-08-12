package com.cendrb.rododendron.client.render.bitch;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBitch extends RenderBiped {

	ResourceLocation texture;
	public RenderBitch(ModelBiped par1ModelBiped, float par2, ResourceLocation location) {
		super(par1ModelBiped, par2);
		texture = location;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return texture;
	}

}
