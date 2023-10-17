package code.vi.sys;

import applications.main.LaunchingApplications;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingApplicationsSys extends LaunchingApplications {

    public LaunchingApplicationsSys() {
        this(new DefProgramInfos());
    }
    public LaunchingApplicationsSys(AbstractProgramInfos _p) {
        super(_p, new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator())));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new LaunchingApplicationsSys());
    }
}
