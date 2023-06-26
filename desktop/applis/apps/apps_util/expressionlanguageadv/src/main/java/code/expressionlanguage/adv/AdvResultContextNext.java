package code.expressionlanguage.adv;

import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;

public final class AdvResultContextNext extends AbsAdvResultContextNext {
    private final WindowCdmEditor mainWindow;
    public AdvResultContextNext(WindowCdmEditor _w,AbstractProgramInfos _frames, CdmFactory _progressingTests) {
        super(_frames, _progressingTests);
        mainWindow = _w;
    }
    @Override
    public ResultContext init(Options _opt) {
        ManageOptions man_ = mainWindow.manage(mainWindow.getSoftParams().getLines());
        return init(_opt,man_,new UnitIssuer(mainWindow.getStatusAnalyzeArea()));
    }

}
