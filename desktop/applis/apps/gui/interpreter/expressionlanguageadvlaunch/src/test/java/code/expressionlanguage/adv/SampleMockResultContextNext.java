package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ResultContext;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class SampleMockResultContextNext extends AbsMockResultContextNext {
    public SampleMockResultContextNext(WindowCdmEditor _w, AbstractProgramInfos _frames, CdmFactory _progressingTests) {
        super(_w, _frames, _progressingTests);
    }

    @Override
    protected StringMap<String> predef() {
        return new StringMap<String>();
    }

    @Override
    protected void build(LgNamesGui _fwd) {
        _fwd.buildBase();
    }

    @Override
    public void generate(ResultContext _res) {
        ForwardInfos.generalForward(_res);
        ContextEl ctx_ = generate().gene(_res.getForwards());
        _res.setContext(ctx_);
    }

}
