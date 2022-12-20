package code.vi.sys;

import code.gui.CdmFactory;
import code.renders.LaunchingRenders;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.prot.impl.variant.GraphicListGeneratorStr;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingRendersSys extends LaunchingRenders {
    public LaunchingRendersSys() {
        super(new CdmFactory(new DefProgramInfos(),new DefInterceptor(new DefErrGenerator()),new GraphicListGeneratorStr()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingRenders.loadLaungage(_args,new LaunchingRendersSys());
    }
}
