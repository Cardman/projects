package code.expressionlanguage;

import code.expressionlanguage.fwd.AbsContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractAtomicBoolean;

public final class AdvContextGenerator implements AbsContextGenerator {
    private final AbstractAtomicBoolean stop;

    public AdvContextGenerator(AbstractAtomicBoolean _s) {
        this.stop = _s;
    }

    @Override
    public ContextEl gene(Forwards _f) {
        return ((LgNamesUtils)_f.getGenerator()).newContext(stop, _f.getGenerator().newContextCommon(_f.getOptions(),_f),((LgNamesUtils)_f.getGenerator()).args());
    }
}
