package code.vi.sys;

import aiki.db.DataBase;
import aiki.main.AikiFactory;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.prot.impl.DefaultExecutorServiceParam;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingApplicationsSys extends LaunchingApplications {

    public LaunchingApplicationsSys() {
        this(new DefProgramInfos());
    }
    public LaunchingApplicationsSys(AbstractProgramInfos _p) {
        super(_p, new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator())),new CardFactories(new DefaultExecutorServiceParam<StringMap<StringMap<int[][]>>>()),new AikiFactory(new DefaultExecutorServiceParam<DataBase>()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new LaunchingApplicationsSys());
    }
}
