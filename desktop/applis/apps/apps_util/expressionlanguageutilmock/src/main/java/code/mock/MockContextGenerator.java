package code.mock;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.expressionlanguage.utilcompo.InterruptibleContextEl;
import code.threads.AbstractAtomicBoolean;

public final class MockContextGenerator implements AbsAdvContextGenerator {
    private final AbstractAtomicBoolean stop;

    public MockContextGenerator(AbstractAtomicBoolean _s) {
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

    @Override
    public ContextEl gene(Forwards _f) {
        return new InterruptibleContextEl(getStop(),_f.getGenerator().newContextCommon(_f.getOptions(),_f));
    }
}
