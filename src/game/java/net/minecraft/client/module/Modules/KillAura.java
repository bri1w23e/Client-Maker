package client.module.modules;

import client.module.Category;
import client.module.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class KillAura extends Module {

    private float range = 4.0f;

    public KillAura() {
        super("KillAura", Category.COMBAT, 0);
    }

    @Override
    public void onUpdate() {
        if (mc.thePlayer == null || mc.theWorld == null) return;

        for (Object o : mc.theWorld.loadedEntityList) {
            if (!(o instanceof EntityLivingBase)) continue;

            EntityLivingBase target = (EntityLivingBase) o;

            // Skip self
            if (target == mc.thePlayer) continue;

            // Skip dead entities
            if (target.isDead || target.getHealth() <= 0) continue;

            // Distance check
            if (mc.thePlayer.getDistanceToEntity(target) > range) continue;

            // Optional: only attack players
            if (target instanceof EntityPlayer) {
                attack(target);
            }
        }
    }

    private void attack(EntityLivingBase target) {
        mc.thePlayer.swingItem();
        mc.playerController.attackEntity(mc.thePlayer, target);
    }
}
