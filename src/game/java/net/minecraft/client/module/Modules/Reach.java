public class Reach extends Module {

    private double reachDistance = 4.5;

    public Reach() {
        super("Reach", Category.COMBAT, 0);
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
        // Nothing needed here — the hook reads reachDistance
    }

    public double getReachDistance() {
        return reachDistance;
    }

    public void setReachDistance(double reachDistance) {
        this.reachDistance = reachDistance;
    }
}
