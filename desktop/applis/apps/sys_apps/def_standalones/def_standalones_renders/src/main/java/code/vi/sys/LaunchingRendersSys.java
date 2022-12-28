package code.vi.sys;

import code.gui.CdmFactory;
import code.gui.CustGraphicListGeneratorStruct;
import code.gui.initialize.AbstractProgramInfos;
import code.renders.LaunchingRenders;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.prot.impl.variant.GraphicListGeneratorStr;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingRendersSys extends LaunchingRenders {
    public LaunchingRendersSys() {
        this(new DefProgramInfos());
    }
    public LaunchingRendersSys(AbstractProgramInfos _p) {
        super(_p,new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator()),new GraphicListGeneratorStr(),new CustGraphicListGeneratorStruct()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingRenders.loadLaungage(_args,new LaunchingRendersSys());
    }
}
