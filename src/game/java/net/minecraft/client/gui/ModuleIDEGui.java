import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

public class ModuleIDEGui extends GuiScreen {

    private final ModuleIDE parent;
    private GuiTextField nameField;
    private GuiButton categoryButton;
    private GuiButton createButton;

    private Category currentCategory = Category.MISC;

    public ModuleIDEGui(ModuleIDE parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        int centerX = width / 2;

        nameField = new GuiTextField(0, fontRendererObj, centerX - 100, 60, 200, 20);
        nameField.setFocused(true);

        categoryButton = new GuiButton(1, centerX - 100, 100, 200, 20,
                "Category: " + currentCategory.name());

        createButton = new GuiButton(2, centerX - 100, 140, 200, 20,
                "Create Module");

        buttonList.add(categoryButton);
        buttonList.add(createButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            // Cycle categories
            int next = (currentCategory.ordinal() + 1) % Category.values().length;
            currentCategory = Category.values()[next];
            categoryButton.displayString = "Category: " + currentCategory.name();
        }

        if (button.id == 2) {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                ModuleTemplateGenerator.generate(name, currentCategory);
                mc.thePlayer.addChatMessage(
                        new ChatComponentText("§aGenerated module: " + name)
                );
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        nameField.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Module IDE", width / 2, 20, 0xFFFFFF);
        nameField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
