package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
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
            addLocalWrapper(v.getName(),new VariableWrapper(LocalVariable.newLocalVariable(NullStruct.NULL_VALUE,_aliasObject)));
            cacheInfo.getCacheLocalNames().add(new NameAndType(v.getName(),v.getType()));
        }
        for (NameAndType v: _fct.getCacheInfo().getCacheLoopNames()) {
            LoopVariable l_ = new LoopVariable();
            l_.setIndexClassName(_aliasObject);
            l_.setIndex(-1);
            addLoop(v.getName(),l_);
            cacheInfo.getCacheLoopNames().add(new NameAndType(v.getName(),v.getType()));
        }
    }

    private void setCache(ExecRootBlock _rootBlock, String _globalClass, ContextEl _context, StackCall _stackCall) {
        int i_ = 0;
        for (NameAndType v: cacheInfo.getCacheLocalNames()) {
            String cl_ = ExecInherits.quickFormat(_rootBlock, _globalClass, v.getType());
            setClassLocalValueWrapper(i_,cl_,_context, _stackCall);
            i_++;
        }
        i_ = 0;
        for (NameAndType v: cacheInfo.getCacheLoopNames()) {
            String cl_ = ExecInherits.quickFormat(_rootBlock, _globalClass, v.getType());
            setClassLoopValue(i_,cl_);
            i_++;
        }
    }
    public Struct checkCache(ExecRootBlock _rootBlock, String _classNameFound, ContextEl _context, StackCall _stackCall) {
        setCache(_rootBlock, _classNameFound, _context, _stackCall);
        for (NamedWrapper v: locWrappers()) {
            AbstractWrapper localVariable_ = v.getWrapper();
            Struct struct_ = ExecTemplates.checkObjectEx(v.getClassName(), Argument.getNull(localVariable_.getValue(_stackCall, _context)).getClassName(_context), _context, _stackCall);
            if (struct_ != null) {
                return struct_;
            }
        }
        return null;
    }
}
