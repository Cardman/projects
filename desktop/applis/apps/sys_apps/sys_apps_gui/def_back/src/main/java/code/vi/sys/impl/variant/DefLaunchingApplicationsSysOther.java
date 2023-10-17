package code.vi.sys.impl.variant;

import applications.main.LaunchingApplications;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;

public final class DefLaunchingApplicationsSysOther extends LaunchingApplications {
    public DefLaunchingApplicationsSysOther() {
        this(new DefOtherProgramInfos());
    }
    public DefLaunchingApplicationsSysOther(AbstractProgramInfos _p) {
        super(_p, new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator())));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new DefLaunchingApplicationsSysOther());
    }
}
