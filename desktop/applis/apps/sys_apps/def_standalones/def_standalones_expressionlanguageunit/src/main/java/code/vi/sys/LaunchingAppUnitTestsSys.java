package code.vi.sys;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.gui.CdmFactory;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.prot.impl.variant.GraphicListGeneratorStr;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingAppUnitTestsSys extends LaunchingAppUnitTests {
    public LaunchingAppUnitTestsSys() {
        super(new CdmFactory(new DefProgramInfos(),new DefInterceptor(new DefErrGenerator()),new GraphicListGeneratorStr()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingAppUnitTests.loadLaungage(_args,new LaunchingAppUnitTestsSys());
    }
}
