package com.cendrb.rododendron.entity.ai;

import com.cendrb.rododendron.entity.bitch.EntityYvonne;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIYvonneFuck extends EntityAIBase {

	EntityYvonne fuckingYvonne;
	EntityLivingBase yvonneAttackTarget;
	
	public EntityAIYvonneFuck(EntityYvonne yvonne)
	{
		fuckingYvonne = yvonne;
	}
	
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.fuckingYvonne.getAttackTarget();
        return this.fuckingYvonne.getPrimed() || entitylivingbase != null && this.fuckingYvonne.getDistanceSqToEntity(entitylivingbase) < 30.0D;
	}
	
	/**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        fuckingYvonne.getNavigator().clearPathEntity();
        yvonneAttackTarget = fuckingYvonne.getAttackTarget();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.yvonneAttackTarget = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.yvonneAttackTarget == null)
        {
            this.fuckingYvonne.setPrimed(false);
        }
        else if (this.fuckingYvonne.getDistanceSqToEntity(this.yvonneAttackTarget) > 69.0D)
        {
        	this.fuckingYvonne.setPrimed(false);
        }
        else if (!this.fuckingYvonne.getEntitySenses().canSee(this.yvonneAttackTarget))
        {
        	this.fuckingYvonne.setPrimed(false);
        }
        else
        {
        	this.fuckingYvonne.setPrimed(true);
        }
    }

}
