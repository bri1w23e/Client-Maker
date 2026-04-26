public class Scaffold extends Module {

    public Scaffold() {
        super("Scaffold", Category.WORLD, 0);
    }

    @Override
    public void onEnable() {
        // Optional: actions when enabled
    }

    @Override
    public void onDisable() {
        // Optional: actions when disabled
    }

    @Override
    public void onUpdate() {
        if (!isEnabled()) return;

        // Make sure the player exists
        if (mc.thePlayer == null || mc.theWorld == null) return;

        // Player position
        double x = mc.thePlayer.posX;
        double y = mc.thePlayer.posY - 1.0;
        double z = mc.thePlayer.posZ;

        BlockPos blockPos = new BlockPos(x, y, z);

        // If there is no block under the player, place one
        if (mc.theWorld.isAirBlock(blockPos)) {
            int slot = getBlockSlot();

            if (slot != -1) {
                int previousSlot = mc.thePlayer.inventory.currentItem;
                mc.thePlayer.inventory.currentItem = slot;

                mc.playerController.onPlayerRightClick(
                        mc.thePlayer,
                        mc.theWorld,
                        mc.thePlayer.getHeldItem(),
                        blockPos,
                        EnumFacing.UP,
                        new Vec3(0.5, 0.5, 0.5)
                );

                mc.thePlayer.inventory.currentItem = previousSlot;
            }
        }
    }

    // Finds a hotbar slot containing placeable blocks
    private int getBlockSlot() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() instanceof ItemBlock) {
                return i;
            }
        }
        return -1;
    }
}
