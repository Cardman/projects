package code.expressionlanguage.exec.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.WithCache;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ShownCache extends Cache {
    private final CacheInfo cacheInfo = new CacheInfo();
    public ShownCache(WithCache _fct, String _aliasObject) {
        for (NameAndType v: _fct.getCacheInfo().getCacheLocalNames()) {
            locWrappers().add(new NamedWrapper(v.getName(),new ReflectVariableWrapper(LocalVariable.newLocalVariable(NullStruct.NULL_VALUE,_aliasObject))));
            cacheInfo.getCacheLocalNames().add(new NameAndType(v.getName(),v.getType()));
        }
        for (NameAndType v: _fct.getCacheInfo().getCacheLoopNames()) {
            LoopVariable l_ = new LoopVariable();
            l_.setIndexClassName(_aliasObject);
            l_.setIndex(-1);
            loopVars().add(new NamedLoopVariable(v.getName(), l_));
            cacheInfo.getCacheLoopNames().add(new NameAndType(v.getName(),v.getType()));
        }
    }

    public Struct checkCache(ExecFormattedRootBlock _classNameFound, ContextEl _context, StackCall _stackCall) {
        int i_ = 0;
        for (NameAndType v: cacheInfo.getCacheLoopNames()) {
            String cl_ = ExecInherits.quickFormat(_classNameFound, v.getType());
            loopVars().get(i_).getLocalVariable().setIndexClassName(cl_);
            i_++;
        }
        i_ = 0;
        for (NameAndType v: cacheInfo.getCacheLocalNames()) {
            String cl_ = ExecInherits.quickFormat(_classNameFound, v.getType());
            NamedWrapper namedWrapper_ = locWrappers().get(i_);
            AbstractWrapper wrapper_ = namedWrapper_.getWrapper();
            Struct struct_ = ExecTemplates.checkObjectEx(cl_, wrapper_.getClassName(_stackCall, _context), _context, _stackCall);
            if (struct_ != null) {
                return struct_;
            }
            i_++;
        }
        return null;
    }
}
