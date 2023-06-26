package code.expressionlanguage.utilimpl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.threads.AbstractAtomicBoolean;

public final class MemoContextGenerator implements AbsLightContextGenerator {
    private final AbstractAtomicBoolean stop;
    public MemoContextGenerator(AbstractAtomicBoolean _a) {
        stop = _a;
    }

    @Override
    public ContextEl gene(Forwards _f) {
        return ((LgNamesWithNewAliases)_f.getGenerator()).newContext(stop,_f.getGenerator().newContextCommon(_f.getOptions(),_f),((LgNamesWithNewAliases)_f.getGenerator()).args());
    }
}
