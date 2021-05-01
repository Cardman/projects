package code.expressionlanguage.exec.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.NamedLoopVariable;
import code.expressionlanguage.exec.variables.NamedWrapper;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;
import code.util.StringMap;

public final class HiddenCache extends Cache {
    public HiddenCache(AbstractPageEl _cont) {
        this(_cont.getRefParams(),_cont.getVars(),_cont.getCache());
    }
    public HiddenCache(StringMap<AbstractWrapper> _refPar, StringMap<LoopVariable> _loop, Cache _cache) {
        for (EntryCust<String, AbstractWrapper> v: _refPar.entryList()) {
            AbstractWrapper value_ = v.getValue();
            locWrappers().add(new NamedWrapper(v.getKey(),ExecTemplates.getWrap(value_),""));
        }
        for (EntryCust<String, LoopVariable> v: _loop.entryList()) {
            LoopVariable value_ = v.getValue();
            LoopVariable l_ = new LoopVariable();
            l_.setIndexClassName(value_.getIndexClassName());
            l_.setIndex(value_.getIndex());
            loopVars().add(new NamedLoopVariable(v.getKey(), l_));
        }
        if (_cache != null) {
            for (NamedWrapper v: _cache.locWrappers()) {
                AbstractWrapper value_ = v.getWrapper();
                locWrappers().add(new NamedWrapper(v.getName(), ExecTemplates.getWrap(value_),""));
            }
            for (NamedLoopVariable v: _cache.loopVars()) {
                LoopVariable value_ = v.getLocalVariable();
                LoopVariable l_ = new LoopVariable();
                l_.setIndexClassName(value_.getIndexClassName());
                l_.setIndex(value_.getIndex());
                loopVars().add(new NamedLoopVariable(v.getName(), l_));
            }
        }
    }
    public Struct checkCache(ExecRootBlock _rootBlock, String _classNameFound, ContextEl _context, StackCall _stackCall) {
        return null;
    }
}
