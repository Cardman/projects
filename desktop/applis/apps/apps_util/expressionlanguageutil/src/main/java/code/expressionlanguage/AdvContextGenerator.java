package code.expressionlanguage;

import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.threads.AbstractAtomicBoolean;

public final class AdvContextGenerator implements AbsAdvContextGenerator {
    private final AbstractAtomicBoolean stop;

    public AdvContextGenerator(AbstractAtomicBoolean _s) {
        this.stop = _s;
    }

    @Override
    public AbstractAtomicBoolean getStop() {
        return stop;
    }

    @Override
    public ContextEl geneWith(Forwards _f) {
        ContextEl c_ = gene(_f);
        RunnableStruct.setupThread((RunnableContextEl) c_);
        return c_;
    }

    @Override
    public ContextEl gene(Forwards _f) {
        return ((LgNamesWithNewAliases)_f.getGenerator()).newContext(getStop(), _f.getGenerator().newContextCommon(_f.getOptions(),_f),((LgNamesWithNewAliases)_f.getGenerator()).args());
    }
}
