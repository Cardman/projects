package code.vi.sys;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.gui.WithAppFactories;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingAppUnitTestsSys extends LaunchingAppUnitTests {
    public LaunchingAppUnitTestsSys() {
        this(new DefProgramInfos());
    }
    public LaunchingAppUnitTestsSys(WithAppFactories _p) {
        super(_p);
    }
    public static void loadLaungage(String[] _args) {
        LaunchingAppUnitTests.loadLaungage(_args,new LaunchingAppUnitTestsSys());
    }
}
