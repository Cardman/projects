package code.expressionlanguage.utilimpl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.Forwards;
import code.mock.MockAbsContextGenerator;
import code.threads.AbstractAtomicBoolean;

public final class SampleMockGeneExec extends MockAbsContextGenerator {
    public SampleMockGeneExec(AbstractAtomicBoolean _s) {
        super(_s);
    }

    @Override
    public ContextEl gene(Forwards _f) {
        return new SampleInterruptedContextEl(getStop(),_f.getGenerator().newContextCommon(_f.getOptions(),_f));
    }
}
