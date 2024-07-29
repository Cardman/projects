package code.vi.sys;

import applications.code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingAppUnitTestsSys extends LaunchingAppUnitTests {
    public LaunchingAppUnitTestsSys() {
        super(DefProgramInfos.build());
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingAppUnitTestsSys().loadLanguage(TEMP_FOLDER,_args);
    }
}
