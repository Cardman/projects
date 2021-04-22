package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ArrayStruct extends WithoutParentIdStruct implements Struct {

    private final Struct[] instance;

    private final String className;

    public ArrayStruct(int _len, String _className) {
        instance = new Struct[_len];
        className = StringUtil.nullToEmpty(_className);
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
        return className;
    }

    public String getClassName() {
        return className;
    }

    public int getLength() {
        return instance.length;
    }
    public Struct get(int _i) {
        return Argument.getNull(instance[_i]);
    }
    public void set(int _i, Struct _str) {
        instance[_i]=_str;
    }
    public Struct[] getInstance() {
        return instance;
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }
    public CustList<Argument> listArgs() {
        CustList<Argument> args_ = new CustList<Argument>(new CollCapacity(instance.length));
        for (Struct a: instance) {
            Argument a_ = new Argument(a);
            args_.add(a_);
        }
        return args_;
    }

    public CustList<Struct> list() {
        CustList<Struct> args_ = new CustList<Struct>(new CollCapacity(instance.length));
        for (Struct a: instance) {
            args_.add(Argument.getNull(a));
        }
        return args_;
    }
}
