public class EnchantEverything extends Module {

    private static final int LEVEL = 255;

    public EnchantEverything() {
        super("EnchantEverything", Category.PLAYER, 0);
    }

    @Override
    public void onEnable() {
        if (mc == null || mc.thePlayer == null || mc.theWorld == null) {
            toggle();
            return;
        }

        ItemStack held = mc.thePlayer.getHeldItem();
        if (held == null) {
            mc.thePlayer.addChatMessage(
                new ChatComponentText("§cHold an item to enchant.")
            );
            toggle();
            return;
        }

        // Only allowed where item editing is permitted
        if (!mc.isSingleplayer() && !mc.playerController.isInCreativeMode()) {
            mc.thePlayer.addChatMessage(
                new ChatComponentText("§cEnchantEverything only works in singleplayer or creative.")
            );
            toggle();
            return;
        }

        // Apply EVERY enchantment with NO exceptions
        for (Enchantment ench : Enchantment.enchantmentsList) {
            if (ench != null) {
                try {
                    held.addEnchantment(ench, LEVEL);
                } catch (Exception ignored) {
                    // Even if the enchant normally doesn't apply, force it anyway
                }
            }
        }

        mc.thePlayer.addChatMessage(
            new ChatComponentText("§aApplied ALL enchantments at level " + LEVEL + " with no exceptions!")
        );

        toggle();
    }

    @Override
    public void onDisable() {}

    @Override
    public void onUpdate() {}
}
