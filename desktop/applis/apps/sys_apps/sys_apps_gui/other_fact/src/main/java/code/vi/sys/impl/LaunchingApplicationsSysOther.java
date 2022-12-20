package code.vi.sys.impl;

import aiki.main.AikiFactory;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.AdvGraphicListGenerator;
import code.vi.prot.impl.AdvGraphicListGeneratorStr;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;

public final class LaunchingApplicationsSysOther extends LaunchingApplications {
    public LaunchingApplicationsSysOther() {
        this(new OtherProgramInfos());
    }
    public LaunchingApplicationsSysOther(AbstractProgramInfos _p) {
        super(_p,new CardFactories(new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>()), new AikiFactory(new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>()),new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator()),new AdvGraphicListGeneratorStr()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new LaunchingApplicationsSysOther());
    }
}
