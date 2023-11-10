package code.mock;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.utilcompo.InterruptibleContextEl;
import code.threads.AbstractAtomicBoolean;

public final class MockContextGenerator extends MockAbsContextGenerator {

    public MockContextGenerator(AbstractAtomicBoolean _s) {
        super(_s);
    }

    @Override
    public ContextEl gene(Forwards _f) {
        return new InterruptibleContextEl(getStop(),_f.getGenerator().newContextCommon(_f.getOptions(),_f));
    }
}
