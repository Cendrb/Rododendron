// Date: 6. 7. 2015 17:36:08
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX


package com.cendrb.rododendron.client.model;

import com.cendrb.rododendron.entity.EntityDun;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelDun extends ModelBiped {
    //fields
    ModelRenderer penis;

    private float dickCounterRotation = 0;
    private float sideCounterRotation = 0;

    public ModelDun() {
        super(0, 0, 128, 64);
        textureWidth = 128;
        textureHeight = 64;

        penis = new ModelRenderer(this, 88, 0);
        penis.addBox(-1.5F, -1.5F, -16F, 3, 3, 17);
        penis.setRotationPoint(0F, 11F, 0F);
        penis.setTextureSize(128, 32);
        penis.setTextureOffset(0, 88);
        penis.mirror = true;
        setRotation(penis, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        penis.render(f5);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);

        EntityDun dun = (EntityDun)p_78086_1_;

        if(dun.getDataWatcher().getWatchableObjectByte(21) == 1) {
            dickCounterRotation += 0.2F;
            if (dickCounterRotation > 6.28F)
                dickCounterRotation = 0;

            sideCounterRotation += 0.2F;
            if (sideCounterRotation > 360)
                sideCounterRotation = 0;

            penis.rotateAngleZ = dickCounterRotation;

            float sideRotation = MathHelper.cos(sideCounterRotation) * 1.2F;
            penis.rotateAngleY = sideRotation;
            penis.rotateAngleX = sideRotation;
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float time, float distance, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(time, distance, f2, f3, f4, f5, entity);

    }

}
