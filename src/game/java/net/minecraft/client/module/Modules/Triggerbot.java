package client.module.modules;

import client.module.Category;
import client.module.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class TriggerBot extends Module {

    public TriggerBot() {
        super("TriggerBot", Category.COMBAT, 0);
    }

    @Override
    public void onUpdate() {
        if (mc.thePlayer == null || mc.theWorld == null) return;

        // Check what the crosshair is pointing at
        MovingObjectPosition target = mc.objectMouseOver;

        if (target == null) return;
        if (target.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) return;

        // Check if it's a living entity
        if (target.entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) target.entityHit;

            // Perform a left-click attack
            mc.clickMouse();
        }
    }
}
