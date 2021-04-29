package code.expressionlanguage.exec.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.NamedLoopVariable;
import code.expressionlanguage.exec.variables.NamedWrapper;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;

public final class HiddenCache extends Cache {
    public HiddenCache() {
    }
    public HiddenCache(AbstractPageEl _cont) {
        for (EntryCust<String, AbstractWrapper> v: _cont.getRefParams().entryList()) {
            AbstractWrapper value_ = v.getValue();
            addLocalWrapper(v.getKey(),value_);
        }
        for (EntryCust<String, LoopVariable> v: _cont.getVars().entryList()) {
            LoopVariable value_ = v.getValue();
            LoopVariable l_ = new LoopVariable();
            l_.setIndexClassName(value_.getIndexClassName());
            l_.setIndex(value_.getIndex());
            addLoop(v.getKey(),l_);
        }
        Cache cache_ = _cont.getCache();
        if (cache_ != null) {
            for (NamedWrapper v: cache_.locWrappers()) {
                AbstractWrapper value_ = v.getWrapper();
                addLocalWrapper(v.getName(),value_);
            }
            for (NamedLoopVariable v: cache_.loopVars()) {
                LoopVariable value_ = v.getLocalVariable();
                LoopVariable l_ = new LoopVariable();
                l_.setIndexClassName(value_.getIndexClassName());
                l_.setIndex(value_.getIndex());
                addLoop(v.getName(),l_);
            }
        }
    }
    public Struct checkCache(ExecRootBlock _rootBlock, String _classNameFound, ContextEl _context, StackCall _stackCall) {
        return null;
    }
}
