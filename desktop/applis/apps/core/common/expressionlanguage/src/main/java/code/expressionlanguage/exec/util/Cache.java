package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class Cache {
    private final CustList<NamedWrapper> localWrappers = new CustList<NamedWrapper>();
    private final CustList<NamedLoopVariable> loopVariables = new CustList<NamedLoopVariable>();

    public abstract Struct checkCache(ExecRootBlock _rootBlock, String _classNameFound, ContextEl _context, StackCall _stackCall);
    public StringList getLocalWrappers() {
        StringList list_ = new StringList();
        for (NamedWrapper n: localWrappers) {
            list_.add(n.getName());
        }
        return list_;
    }
    protected CustList<NamedWrapper> locWrappers() {
        return localWrappers;
    }
    protected CustList<NamedLoopVariable> loopVars() {
        return loopVariables;
    }
    public LongStruct getLocalWrapperCount(String _key) {
        long index_ = 0;
        for (NamedWrapper n: localWrappers) {
            if (StringUtil.quickEq(n.getName(),_key)) {
                index_++;
            }
        }
        return new LongStruct(index_);
    }
    public Struct getLocalWrapperValue(String _key, long _var, ContextEl _context, StackCall _stackCall) {
        long index_ = 0;
        for (NamedWrapper n: localWrappers) {
            if (StringUtil.quickEq(n.getName(),_key)) {
                if (index_ == _var) {
                    return n.getWrapper().getValue(_stackCall, _context);
                }
                index_++;
            }
        }
        return NullStruct.NULL_VALUE;
    }
    public AbstractWrapper getLocalWrapper(String _key, long _var) {
        long index_ = 0;
        for (NamedWrapper n: localWrappers) {
            if (StringUtil.quickEq(n.getName(),_key)) {
                if (index_ == _var) {
                    return n.getWrapper();
                }
                index_++;
            }
        }
        return null;
    }
    public void putLocalWrapperValue(String _key, Struct _var, ContextEl _context, StackCall _stackCall) {
        for (NamedWrapper n: localWrappers) {
            if (StringUtil.quickEq(n.getName(),_key)) {
                n.getWrapper().setValue(_stackCall, _context,new Argument(_var));
                break;
            }
        }

    }
    public void putLocalWrapperValue(String _key, long _index, Struct _var, ContextEl _context, StackCall _stackCall) {
        long index_ = 0;
        for (NamedWrapper n: localWrappers) {
            if (StringUtil.quickEq(n.getName(),_key)) {
                if (index_ == _index) {
                    n.getWrapper().setValue(_stackCall, _context,new Argument(_var));
                    break;
                }
                index_++;
            }
        }
    }
    public StringList getLoopVars() {
        StringList list_ = new StringList();
        for (NamedLoopVariable n: loopVariables) {
            list_.add(n.getName());
        }
        return list_;
    }
    public Struct getLoopValue(String _key, long _var) {
        LoopVariable loop_ = getLoopVar(_key,_var);
        if (loop_ != null) {
            return new LongStruct(loop_.getIndex());
        }
        return new LongStruct(-1);
    }
    public LoopVariable getLoopVar(String _key, long _var) {
        long index_ = 0;
        for (NamedLoopVariable n: loopVariables) {
            if (StringUtil.quickEq(n.getName(),_key)) {
                if (index_ == _var) {
                    return n.getLocalVariable();
                }
                index_++;
            }
        }
        return null;
    }
    public void putLoopValue(String _key,long _index, long _var) {
        long index_ = 0;
        for (NamedLoopVariable n: loopVariables) {
            if (StringUtil.quickEq(n.getName(),_key)) {
                if (index_ == _index) {
                    n.getLocalVariable().setIndex(_var);
                    break;
                }
                index_++;
            }
        }
    }
    public void putLoopValue(String _key, long _var) {
        for (NamedLoopVariable n: loopVariables) {
            if (StringUtil.quickEq(n.getName(),_key)) {
                n.getLocalVariable().setIndex(_var);
                break;
            }
        }
    }
}
