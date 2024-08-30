package code.vi.sys;

import applications.code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.utilcompo.FileInfos;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingAppUnitTestsSys extends LaunchingAppUnitTests {
    public LaunchingAppUnitTestsSys() {
        super(DefProgramInfos.build(FileInfos.CDM));
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingAppUnitTestsSys().loadLanguage(_args);
    }
}
