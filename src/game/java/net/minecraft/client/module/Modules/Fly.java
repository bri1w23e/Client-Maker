public class Fly extends Module {

    public Fly() {
        super("Fly", Category.MOVEMENT, Keyboard.KEY_G);
    }

    @Override
    public void onUpdate() {
        mc.thePlayer.motionY = 0;
        mc.thePlayer.capabilities.isFlying = true;
    }
}
