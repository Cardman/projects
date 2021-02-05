package code.sys;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.sys.impl.GraphicComboBoxGenerator;
import code.sys.impl.GraphicStringListGenerator;
import code.sys.impl.ProgramInfos;

public class LaunchingAppUnitTestsSys extends LaunchingAppUnitTests {
    public LaunchingAppUnitTestsSys() {
        super(new ProgramInfos(new GraphicStringListGenerator(), new GraphicComboBoxGenerator()));
    }
}
