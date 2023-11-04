package code.expressionlanguage.adv;

import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;

public final class AdvResultContextNext extends AdvAbsResultContextNext {
    public AdvResultContextNext(WindowCdmEditor _w,AbstractProgramInfos _frames, CdmFactory _progressingTests) {
        super(_w,_frames, _progressingTests);
    }
    @Override
    public ResultContext init(Options _opt, boolean _light) {
        ManageOptions man_ = getMainWindow().manage(getMainWindow().getSoftParams().getLines());
        return init(_opt,man_,new UnitIssuer(getMainWindow().getStatusAnalyzeArea()),_light);
    }

    @Override
    public void generate(ResultContext _r) {
        ResultContext.fwdWithoutCheck(_r,generate());
    }

}
