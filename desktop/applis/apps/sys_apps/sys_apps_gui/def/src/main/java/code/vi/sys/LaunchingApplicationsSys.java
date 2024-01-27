package code.vi.sys;

import applications.main.LaunchingApplications;
import code.gui.WithAppFactories;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingApplicationsSys extends LaunchingApplications {

    public LaunchingApplicationsSys() {
        this(DefProgramInfos.build());
    }
    public LaunchingApplicationsSys(WithAppFactories _p) {
        super(_p);
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new LaunchingApplicationsSys());
    }
}
