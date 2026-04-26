import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AimBot extends Module {

    private float range = 6.0f;
    private float fov = 60.0f;      // field of view in degrees
    private float speed = 6.0f;     // how fast to rotate (higher = snappier)

    public AimBot() {
        super("AimBot", Category.COMBAT, 0);
    }

    @Override
    public void onUpdate() {
        if (!isEnabled()) return;
        if (mc.thePlayer == null || mc.theWorld == null) return;

        EntityLivingBase target = getClosestTarget();
        if (target == null) return;

        float[] rotations = getRotationsToEntity(target);
        float yaw = rotations[0];
        float pitch = rotations[1];

        // Smoothly rotate toward target
        mc.thePlayer.rotationYaw = smoothRotate(mc.thePlayer.rotationYaw, yaw, speed);
        mc.thePlayer.rotationPitch = smoothRotate(mc.thePlayer.rotationPitch, pitch, speed);
    }

    private EntityLivingBase getClosestTarget() {
        EntityLivingBase closest = null;
        double closestDist = range;

        List<Entity> entities = mc.theWorld.loadedEntityList;
        for (Entity e : entities) {
            if (!(e instanceof EntityLivingBase)) continue;
            EntityLivingBase ent = (EntityLivingBase) e;

            if (ent == mc.thePlayer) continue;
            if (ent.isDead || ent.getHealth() <= 0) continue;

            double dist = mc.thePlayer.getDistanceToEntity(ent);
            if (dist > range) continue;

            if (!isInFov(ent, fov)) continue;

            if (dist < closestDist) {
                closestDist = dist;
                closest = ent;
            }
        }

        return closest;
    }

    private boolean isInFov(EntityLivingBase entity, float fov) {
        float[] rotations = getRotationsToEntity(entity);
        float yawToTarget = rotations[0];

        float yawDiff = wrapAngleTo180(mc.thePlayer.rotationYaw - yawToTarget);
        return Math.abs(yawDiff) <= fov / 2.0f;
    }

    private float[] getRotationsToEntity(Entity entity) {
        double diffX = entity.posX - mc.thePlayer.posX;
        double diffZ = entity.posZ - mc.thePlayer.posZ;
        double diffY = (entity.posY + entity.getEyeHeight()) - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());

        double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);

        float yaw = (float) (Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F);
        float pitch = (float) -Math.toDegrees(Math.atan2(diffY, dist));

        return new float[]{yaw, pitch};
    }

    private float smoothRotate(float current, float target, float speed) {
        float diff = wrapAngleTo180(target - current);
        if (Math.abs(diff) > speed) {
            diff = diff > 0 ? speed : -speed;
        }
        return current + diff;
    }

    private float wrapAngleTo180(float angle) {
        angle %= 360.0F;
        if (angle >= 180.0F) angle -= 360.0F;
        if (angle < -180.0F) angle += 360.0F;
        return angle;
    }

    // Optional setters for GUI/commands
    public void setRange(float range) { this.range = range; }
    public void setFov(float fov) { this.fov = fov; }
    public void setSpeed(float speed) { this.speed = speed; }
}
