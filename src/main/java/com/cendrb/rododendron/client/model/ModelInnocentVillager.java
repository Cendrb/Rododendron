package com.cendrb.rododendron.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;

/**
 * Created by cendr_000 on 13.08.2015.
 */
public class ModelInnocentVillager extends ModelVillager {
    ModelRenderer pureInnocence;

    public ModelInnocentVillager() {
        super(0, 0, 64, 64);
        pureInnocence = new ModelRenderer(this, 44, 0);
        pureInnocence.addBox(0F, 0F, 0F, 6, 9, 4);
        pureInnocence.setRotationPoint(-3F, 1F, 1F);
        pureInnocence.setTextureSize(64, 32);
        pureInnocence.mirror = true;
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        pureInnocence.render(p_78088_7_);
    }
}
