public class ModuleIDE extends Module {

    private String newModuleName = "";
    private Category selectedCategory = Category.MISC;
    private int keybind = 0;

    public ModuleIDE() {
        super("ModuleIDE", Category.MISC, 0);
    }

    @Override
    public void onEnable() {
        // Open your ClickGUI or custom screen
        mc.displayGuiScreen(new ModuleIDEGui(this));
    }

    @Override
    public void onDisable() {}
}
