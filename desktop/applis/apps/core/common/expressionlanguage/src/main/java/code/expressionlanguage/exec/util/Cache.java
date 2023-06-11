package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class Cache {
    private final CustList<NamedWrapper> localWrappers = new CustList<NamedWrapper>();
    private final CustList<NamedLoopVariable> loopVariables = new CustList<NamedLoopVariable>();

    public abstract Struct checkCache(ExecFormattedRootBlock _classNameFound, ContextEl _context, StackCall _stackCall);
    public StringList getLocalWrappers() {
        StringList list_ = new StringList();
        for (NamedWrapper n: localWrappers) {
            list_.add(n.getName());
        }
        return list_;
    }
    public static void sortByDeepThenName(CustList<ViewVariable> _ls) {
        _ls.sortElts(new ViewVariableDeepNameCmp());
    }
    public static CustList<ViewVariable> view(StackCall _st, AbstractPageEl _page, ContextEl _ctx) {
        CustList<ViewVariable> v_ = new CustList<ViewVariable>();
        Cache c_ = _page.getCache();
        if (c_ != null) {
            StringMap<Integer> counts_ = new StringMap<Integer>();
            for (NamedWrapper n: c_.locWrappers()) {
                int current_;
                if (counts_.contains(n.getName())) {
                    current_ = counts_.getVal(n.getName())+1;
                } else {
                    current_ = 0;
                }
                v_.add(new ViewVariable(n.getName(),n.getWrapper(),_st,_page,current_,_ctx));
                counts_.put(n.getName(),current_);
            }
        }
        for (EntryCust<String, AbstractWrapper> n: _page.getContentEx().getRefParams().entryList()) {
            v_.add(new ViewVariable(n.getKey(),n.getValue(),_st,_page,_ctx));
        }
        return v_;
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
