package com.cendrb.rododendron.client.render;

import com.cendrb.rododendron.reference.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cendr_000 on 13.08.2015.
 */
public class RenderInnocentVillager extends RenderLiving {
    private static final ResourceLocation textureLocation = new ResourceLocation(Reference.MOD_ID, "textures/entity/innocentVillager.png");

    public RenderInnocentVillager(ModelBase p_i1262_1_, float p_i1262_2_) {
        super(p_i1262_1_, p_i1262_2_);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return textureLocation;
    }
}
