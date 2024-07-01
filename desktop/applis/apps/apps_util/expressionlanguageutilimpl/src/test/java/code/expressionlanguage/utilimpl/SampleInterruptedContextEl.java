package code.expressionlanguage.utilimpl;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.utilcompo.InterruptibleContextEl;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.threads.AbstractAtomicBoolean;

public final class SampleInterruptedContextEl extends InterruptibleContextEl {
    public SampleInterruptedContextEl(AbstractAtomicBoolean _i, CommonExecutionInfos _executionInfos) {
        super(_i, _executionInfos);
    }

    @Override
    public void forwardAndClear() {
        super.forwardAndClear();
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) getStandards();
        standards_.getExecContent().getExecutingBlocks().groupClassMethodTests(standards_.getContent(), standards_.getExecContent().getCustAliases(),getClasses());
        standards_.getExecContent().getExecutingBlocks().groupClassTests(standards_.getExecContent().getCustAliases(),getClasses());
        standards_.getExecContent().getExecutingBlocks().exec(standards_.getContent(), standards_.getExecContent().getCustAliases(),getClasses());
    }
}
