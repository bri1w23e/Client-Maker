public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.RENDER, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        mc.gameSettings.gammaSetting = 1000f;
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = 1f;
    }
}
