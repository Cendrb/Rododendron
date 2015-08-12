package com.cendrb.rododendron.client.render.bitch;

import com.cendrb.rododendron.reference.Reference;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Cendrb on 28. 8. 2014.
 */
public class RenderStepanek extends RenderBiped {
    public RenderStepanek(ModelBiped p_i1257_1_, float p_i1257_2_) {
        super(p_i1257_1_, p_i1257_2_);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/stepanek.png");
    }
}
