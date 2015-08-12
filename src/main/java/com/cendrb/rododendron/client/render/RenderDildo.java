package com.cendrb.rododendron.client.render;

import com.cendrb.rododendron.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by cendr_000 on 6. 7. 2015.
 */
@SideOnly(Side.CLIENT)
public class RenderDildo extends RenderLiving {

    private static final ResourceLocation textureLocation = new ResourceLocation(Reference.MOD_ID, "textures/entity/dildo.png");

    public RenderDildo(ModelBase p_i1262_1_, float p_i1262_2_) {
        super(p_i1262_1_, p_i1262_2_);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return textureLocation;
    }
}
