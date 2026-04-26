public class MeteorGui extends GuiScreen {

    private int selectedCategory = 0;
    private final String[] categories = {
        "Combat", "Movement", "Render", "Player", "Misc"
    };

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        // Main window
        drawRoundedRect(50, 30, width - 50, height - 30, 8, 0xFF1A1A1A);

        // Sidebar
        drawRoundedRect(50, 30, 150, height - 30, 8, 0xFF111111);

        // Category buttons
        int y = 50;
        for(int i = 0; i < categories.length; i++) {
            int color = (i == selectedCategory) ? 0xFF6A5ACD : 0xFF2A2A2A;
            drawRoundedRect(60, y, 140, y + 20, 6, color);
            fontRendererObj.drawString(categories[i], 70, y + 6, 0xFFFFFFFF);
            y += 25;
        }

        // TODO: draw modules for selected category
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        // TODO: detect sidebar clicks
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
public void drawRoundedRect(int left, int top, int right, int bottom, int radius, int color) {
    drawRect(left + radius, top, right - radius, bottom, color);
    drawRect(left, top + radius, right, bottom - radius, color);

    // Corners
    drawCircle(left + radius, top + radius, radius, color);
    drawCircle(right - radius, top + radius, radius, color);
    drawCircle(left + radius, bottom - radius, radius, color);
    drawCircle(right - radius, bottom - radius, radius, color);
}
int modY = 50;
for(Module m : modules.get(categories[selectedCategory])) {
    drawRoundedRect(170, modY, width - 60, modY + 20, 6, m.isEnabled() ? 0xFF6A5ACD : 0xFF2A2A2A);
    fontRendererObj.drawString(m.getName(), 180, modY + 6, 0xFFFFFFFF);
    modY += 25;
}
if(mouseX >= 170 && mouseX <= width - 60 && mouseY >= modY && mouseY <= modY + 20) {
    m.toggle();
}
public float easeOutQuad(float t) {
    return 1 - (1 - t) * (1 - t);
}
