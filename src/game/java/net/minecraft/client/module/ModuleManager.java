import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // Add modules here
        modules.add(new KillAura());
        modules.add(new Fly());
        modules.add(new Fullbright());
        modules.add(new TriggerBot());
    }

    public static List<Module> getModulesByCategory(Category c) {
        List<Module> list = new ArrayList<>();
        for(Module m : modules) {
            if(m.getCategory() == c) list.add(m);
        }
        return list;
    }
}
