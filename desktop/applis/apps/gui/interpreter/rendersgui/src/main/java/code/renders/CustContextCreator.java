package code.renders;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.guicompos.EventStruct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;

public final class CustContextCreator {
    public ContextEl newContext(AbstractAtomicBoolean _stop, ContextEl _context) {
        RunnableContextEl r_ = new RunnableContextEl(_stop,null, _context.getExecutionInfos(), new StringList());
        EventStruct.setupThread(r_);
        return r_;
    }

    public ContextEl removeContext(ContextEl _context) {
        ((RunnableContextEl)_context).getCustInit().removeThreadFromList(((RunnableContextEl)_context));
        return _context;
    }
}
