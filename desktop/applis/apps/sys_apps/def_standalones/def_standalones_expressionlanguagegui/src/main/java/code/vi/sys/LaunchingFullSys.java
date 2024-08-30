package code.vi.sys;

import applications.code.expressionlanguage.guicompos.LaunchingFull;
import applications.code.gui.WithAppFactories;
import code.expressionlanguage.utilcompo.FileInfos;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingFullSys extends LaunchingFull {
    public LaunchingFullSys() {
        this(DefProgramInfos.build(FileInfos.CDM));
    }
    public LaunchingFullSys(WithAppFactories _p) {
        super(_p);
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingFullSys().loadLanguage(_args);
    }
}
