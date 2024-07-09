package code.vi.sys;

import code.renders.LaunchingRenders;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingRendersSys extends LaunchingRenders {
    public LaunchingRendersSys() {
        super(DefProgramInfos.build());
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingRendersSys().loadLanguage(TEMP_FOLDER,_args);
    }
}
