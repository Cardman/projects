package code.renders;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.document.AbstractContextCreator;

public final class CustContextCreator implements AbstractContextCreator {
    @Override
    public ContextEl newContext(ContextEl _context) {
        RunnableContextEl r_ = new RunnableContextEl(InitPhase.NOTHING, _context.getExecutionInfos());
        RunnableStruct.setupThread(r_);
        return r_;
    }

    @Override
    public void removeContext(ContextEl _context) {
        ((RunnableContextEl)_context).getCustInit().removeThreadFromList(((RunnableContextEl)_context));
    }
}
