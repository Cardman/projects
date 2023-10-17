package code.vi.sys;

import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingFullSys extends LaunchingFull {
    public LaunchingFullSys() {
        this(new DefProgramInfos());
    }
    public LaunchingFullSys(AbstractProgramInfos _p) {
        super(_p,new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator())));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingFull.loadLaungage(_args,new LaunchingFullSys());
    }
}
