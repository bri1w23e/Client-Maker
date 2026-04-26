package client.module.modules;

import client.module.Category;
import client.module.Module;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Xray extends Module {

    public Xray() {
        super("Xray", Category.RENDER, 0);
    }

    @Override
    public void onEnable() {
        mc.renderGlobal.loadRenderers();
    }

    @Override
    public void onDisable() {
        mc.renderGlobal.loadRenderers();
    }

    public static boolean shouldRender(Block block) {
        // Add blocks you want to see through walls
        return block == Blocks.diamond_ore ||
               block == Blocks.gold_ore ||
               block == Blocks.iron_ore ||
               block == Blocks.emerald_ore ||
               block == Blocks.redstone_ore ||
               block == Blocks.lapis_ore;
    }
}
