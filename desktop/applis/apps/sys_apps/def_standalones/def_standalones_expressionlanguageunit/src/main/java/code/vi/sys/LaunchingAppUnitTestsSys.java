package code.vi.sys;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.gui.CdmFactory;
import code.gui.CustGraphicListGeneratorStruct;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.prot.impl.variant.GraphicListGeneratorStr;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingAppUnitTestsSys extends LaunchingAppUnitTests {
    public LaunchingAppUnitTestsSys() {
        this(new DefProgramInfos());
    }
    public LaunchingAppUnitTestsSys(AbstractProgramInfos _p) {
        super(_p,new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator()),new GraphicListGeneratorStr(),new CustGraphicListGeneratorStruct()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingAppUnitTests.loadLaungage(_args,new LaunchingAppUnitTestsSys());
    }
}
