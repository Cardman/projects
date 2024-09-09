package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ArrayStruct extends WithoutParentIdStruct {

    private final String className;

    private final Struct[] instance;

    public ArrayStruct(int _len, String _className) {
        instance = new Struct[_len];
        className = StringUtil.nullToEmpty(_className);
    }

    public boolean isValid(int _index) {
        return _index >= 0 && _index < getLength();
    }
    public int getLength() {
        return instance.length;
    }
    public Struct get(int _i) {
        return ArgumentListCall.getNull(instance[_i]);
    }
    public void set(int _i, Struct _str) {
        instance[_i]=_str;
    }
    public Struct[] getInstance() {
        return instance;
    }

    public ArrayStruct swallowCopy() {
        int len_ = getLength();
        ArrayStruct copy_ = new ArrayStruct(len_, className);
        for (int i = 0; i < len_; i++) {
            copy_.set(i,get(i));
        }
        return copy_;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return getClassName();
    }

    public String getClassName() {
        return className;
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }
    public CustList<Struct> listArgs() {
        CustList<Struct> args_ = new CustList<Struct>(new CollCapacity(getInstance().length));
        for (Struct a: getInstance()) {
            args_.add(a);
        }
        return args_;
    }

    public CustList<Struct> list() {
        CustList<Struct> args_ = new CustList<Struct>(new CollCapacity(getInstance().length));
        for (Struct a: getInstance()) {
            args_.add(ArgumentListCall.getNull(a));
        }
        return args_;
    }
    public static ArrayStruct instance(String _cl, CustList<Struct> _args) {
        int len_ = _args.size();
        ArrayStruct copy_ = new ArrayStruct(len_, _cl);
        for (int i = 0; i < len_; i++) {
            copy_.set(i, _args.get(i));
        }
        return copy_;
    }
}
