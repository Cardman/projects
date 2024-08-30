package code.vi.sys;

import applications.code.renders.LaunchingRenders;
import code.expressionlanguage.utilcompo.FileInfos;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingRendersSys extends LaunchingRenders {
    public LaunchingRendersSys() {
        super(DefProgramInfos.build(FileInfos.CDM));
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingRendersSys().loadLanguage(_args);
    }
}
