package code.vi.sys;

import code.expressionlanguage.guicompos.LaunchingFull;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingFullSys extends LaunchingFull {
    public LaunchingFullSys() {
        super(new DefProgramInfos());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingFull.loadLaungage(_args,new LaunchingFullSys());
    }
}
