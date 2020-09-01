package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.NamedLocalVariable;
import code.expressionlanguage.exec.variables.NamedLoopVariable;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;

public final class Cache {
    private final CustList<NamedLocalVariable> localVariables = new CustList<NamedLocalVariable>();
    private final CustList<NamedLoopVariable> loopVariables = new CustList<NamedLoopVariable>();
    private final CacheInfo cacheInfo = new CacheInfo();


    public Cache() {
    }
    public Cache(ExecAnonymousFunctionBlock _fct, String _aliasObject) {
        for (NameAndType v: _fct.getCacheInfo().getCacheLocalNames()) {
            addLocal(v.getName(),LocalVariable.newLocalVariable(NullStruct.NULL_VALUE,_aliasObject));
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
    public Cache(PageEl _cont) {
        for (EntryCust<String,LocalVariable> v: _cont.getValueVars().entryList()) {
            LocalVariable value_ = v.getValue();
            Struct struct_ = value_.getStruct();
            addLocal(v.getKey(),LocalVariable.newLocalVariable(struct_,value_.getClassName()));
        }
        for (EntryCust<String,LoopVariable> v: _cont.getVars().entryList()) {
            LoopVariable value_ = v.getValue();
            LoopVariable l_ = new LoopVariable();
            l_.setIndexClassName(value_.getIndexClassName());
            l_.setIndex(value_.getIndex());
            addLoop(v.getKey(),l_);
        }
        Cache cache_ = _cont.getCache();
        if (cache_ != null) {
            for (NamedLocalVariable v: cache_.localVariables) {
                LocalVariable value_ = v.getLocalVariable();
                Struct struct_ = value_.getStruct();
                addLocal(v.getName(),LocalVariable.newLocalVariable(struct_,value_.getClassName()));
            }
            for (NamedLoopVariable v: cache_.loopVariables) {
                LoopVariable value_ = v.getLocalVariable();
                LoopVariable l_ = new LoopVariable();
                l_.setIndexClassName(value_.getIndexClassName());
                l_.setIndex(value_.getIndex());
                addLoop(v.getName(),l_);
            }
        }
    }
    public void setCache(ExecRootBlock _rootBlock, String _globalClass) {
        int i_ = 0;
        for (NameAndType v: cacheInfo.getCacheLocalNames()) {
            String cl_ = ExecTemplates.quickFormat(_rootBlock, _globalClass, v.getType());
            setClassLocalValue(i_,cl_);
            i_++;
        }
        i_ = 0;
        for (NameAndType v: cacheInfo.getCacheLoopNames()) {
            String cl_ = ExecTemplates.quickFormat(_rootBlock, _globalClass, v.getType());
            setClassLoopValue(i_,cl_);
            i_++;
        }
    }
    public Struct checkCache(ContextEl _context) {
        for (NamedLocalVariable v: localVariables) {
            Struct struct_ = ExecTemplates.checkObjectEx(v.getLocalVariable().getClassName(), new Argument(v.getLocalVariable().getStruct()), _context);
            if (struct_ != null) {
                return struct_;
            }
        }
        return null;
    }
    public StringList getLocalVars() {
        StringList list_ = new StringList();
        for (NamedLocalVariable n: localVariables) {
            list_.add(n.getName());
        }
        return list_;
    }
    public Struct getLocalValue(String _key, long _var) {
        LocalVariable loc_ = getLocalVar(_key,_var);
        if (loc_ != null) {
            return loc_.getStruct();
        }
        return NullStruct.NULL_VALUE;
    }
    public LocalVariable getLocalVar(String _key, long _var) {
        long index_ = 0;
        for (NamedLocalVariable n: localVariables) {
            if (StringList.quickEq(n.getName(),_key)) {
                if (index_ == _var) {
                    return n.getLocalVariable();
                }
                index_++;
            }
        }
        return null;
    }
    public void putLocalValue(String _key, Struct _var) {
        for (NamedLocalVariable n: localVariables) {
            if (StringList.quickEq(n.getName(),_key)) {
                n.getLocalVariable().setStruct(_var);
                break;
            }
        }
    }
    public void putLocalValue(String _key, long _index, Struct _var) {
        long index_ = 0;
        for (NamedLocalVariable n: localVariables) {
            if (StringList.quickEq(n.getName(),_key)) {
                if (index_ == _index) {
                    n.getLocalVariable().setStruct(_var);
                    break;
                }
                index_++;
            }
        }
    }
    public void setClassLocalValue(int _index, String _var) {
        if (localVariables.isValidIndex(_index)) {
            localVariables.get(_index).getLocalVariable().setClassName(_var);
        }
    }
    public void addLocal(String _key, LocalVariable _var) {
        localVariables.add(new NamedLocalVariable(_key,_var));
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
            if (StringList.quickEq(n.getName(),_key)) {
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
            if (StringList.quickEq(n.getName(),_key)) {
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
            if (StringList.quickEq(n.getName(),_key)) {
                n.getLocalVariable().setIndex(_var);
                break;
            }
        }
    }
    public void setClassLoopValue(int _index, String _var) {
        if (loopVariables.isValidIndex(_index)) {
            loopVariables.get(_index).getLocalVariable().setIndexClassName(_var);
        }
    }
    public void addLoop(String _key, LoopVariable _var) {
        loopVariables.add(new NamedLoopVariable(_key,_var));
    }
}
