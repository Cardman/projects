package code.expressionlanguage.adv;

import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.options.DefBuildLightResultContextNext;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.DefFileBuilderListGene;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;

public final class AdvResultContextNext extends AdvAbsResultContextNext {
    public AdvResultContextNext(WindowCdmEditor _w,AbstractProgramInfos _frames, CdmFactory _progressingTests) {
        super(_w,_frames, _progressingTests);
    }
    @Override
    public ResultContext init(Options _opt) {
        ManageOptions man_ = getMainWindow().manage(getMainWindow().getSoftParams().getLines());
        FileInfos file_ = baseInit(man_, new UnitIssuer(getMainWindow().getStatusAnalyzeArea()));
        return CustContextFactory.stds(file_, man_.getEx(), _opt, new DefBuildLightResultContextNext(),new DefFileBuilderListGene());
    }

    @Override
    public void generate(ResultContext _r) {
        ResultContext.fwdWithoutCheck(_r,generate());
    }

}
