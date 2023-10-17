package code.vi.sys.impl;

import applications.main.LaunchingApplications;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;

public final class LaunchingApplicationsSysOther extends LaunchingApplications {
    public LaunchingApplicationsSysOther() {
        this(new OtherProgramInfos());
    }
    public LaunchingApplicationsSysOther(AbstractProgramInfos _p) {
        super(_p, new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator())));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new LaunchingApplicationsSysOther());
    }
}
