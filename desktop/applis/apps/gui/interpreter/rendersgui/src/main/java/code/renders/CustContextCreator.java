package code.renders;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.document.AbstractContextCreator;
import code.util.StringList;

public final class CustContextCreator implements AbstractContextCreator {
    @Override
    public ContextEl newContext(ContextEl _context) {
        RunnableContextEl r_ = new RunnableContextEl(null, _context.getExecutionInfos(), new StringList());
        RunnableStruct.setupThread(r_);
        return r_;
    }

    @Override
    public ContextEl removeContext(ContextEl _context) {
        ((RunnableContextEl)_context).getCustInit().removeThreadFromList(((RunnableContextEl)_context));
        return _context;
    }
}
