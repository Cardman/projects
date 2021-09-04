package code.sys;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.sys.impl.DefProgramInfos;
import code.sys.impl.GraphicComboBoxGenerator;
import code.sys.impl.GraphicStringListGenerator;
import code.sys.impl.ProgramInfos;

public final class LaunchingAppUnitTestsSys extends LaunchingAppUnitTests {
    public LaunchingAppUnitTestsSys() {
        super(new DefProgramInfos());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingAppUnitTests.loadLaungage(_args,new LaunchingAppUnitTestsSys());
    }
}
