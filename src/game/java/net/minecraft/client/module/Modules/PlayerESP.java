package client.module.modules;

import client.module.Category;
import client.module.Module;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerESP extends Module {

    public PlayerESP() {
        super("PlayerESP", Category.RENDER, 0);
    }

    @Override
    public void onUpdate() {
        if (mc.theWorld == null || mc.thePlayer == null) return;

        for (Object o : mc.theWorld.loadedEntityList) {
            if (!(o instanceof EntityPlayer)) continue;

            EntityPlayer player = (EntityPlayer) o;

            if (player == mc.thePlayer) continue; // skip self

            drawBox(player);
        }
    }

    private void drawBox(EntityPlayer player) {
        double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * mc.timer.renderPartialTicks - mc.getRenderManager().viewerPosX;
        double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * mc.timer.renderPartialTicks - mc.getRenderManager().viewerPosY;
        double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * mc.timer.renderPartialTicks - mc.getRenderManager().viewerPosZ;

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableDepth();
        GlStateManager.color(1f, 0f, 0f, 1f); // red outline

        mc.getRenderManager().doRenderEntity(player, x, y, z, 0, mc.timer.renderPartialTicks, false);

        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
}
