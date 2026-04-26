public class Timer extends Module {

    private float speed = 0.5f; // 1.0 = normal speed

    public ClientTimer() {
        super("ClientTimer", Category.MISC, 0);
    }

    @Override
    public void onEnable() {
        if (mc == null) {
            toggle();
            return;
        }
        mc.timer.timerSpeed = speed;
    }

    @Override
    public void onDisable() {
        if (mc != null) {
            mc.timer.timerSpeed = 1.0f; // reset
        }
    }

    @Override
    public void onUpdate() {
        if (!isEnabled()) return;
        mc.timer.timerSpeed = speed;
    }

    // For GUI sliders or commands
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
}
