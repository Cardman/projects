package code.vi.sys;

import code.gui.WithAppFactories;
import code.renders.LaunchingRenders;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingRendersSys extends LaunchingRenders {
    public LaunchingRendersSys() {
        this(new DefProgramInfos());
    }
    public LaunchingRendersSys(WithAppFactories _p) {
        super(_p);
    }
    public static void loadLaungage(String[] _args) {
        LaunchingRenders.loadLaungage(_args,new LaunchingRendersSys());
    }
}
