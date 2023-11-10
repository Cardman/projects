package code.mock;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.threads.AbstractAtomicBoolean;

public abstract class MockAbsContextGenerator implements AbsAdvContextGenerator {
    private final AbstractAtomicBoolean stop;

    protected MockAbsContextGenerator(AbstractAtomicBoolean _s) {
        this.stop = _s;
    }

    @Override
    public AbstractAtomicBoolean getStop() {
        return stop;
    }

    @Override
    public ContextEl geneWith(Forwards _f) {
        return gene(_f);
    }
}
