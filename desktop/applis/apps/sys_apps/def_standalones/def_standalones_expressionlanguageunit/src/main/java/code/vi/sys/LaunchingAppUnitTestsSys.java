package code.vi.sys;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingAppUnitTestsSys extends LaunchingAppUnitTests {
    public LaunchingAppUnitTestsSys() {
        super(new DefProgramInfos());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingAppUnitTests.loadLaungage(_args,new LaunchingAppUnitTestsSys());
    }
}
