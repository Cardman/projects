package code.vi.sys;

import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.WithAppFactories;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingFullSys extends LaunchingFull {
    public LaunchingFullSys() {
        this(DefProgramInfos.build());
    }
    public LaunchingFullSys(WithAppFactories _p) {
        super(_p);
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingFullSys().loadLanguage(TEMP_FOLDER,_args);
    }
}
