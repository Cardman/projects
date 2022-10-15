package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ArrayStruct extends AbArrayStruct {

    private final String className;

    public ArrayStruct(int _len, String _className) {
        super(_len);
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
    public String getClassName() {
        return className;
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }
    public CustList<Argument> listArgs() {
        CustList<Argument> args_ = new CustList<Argument>(new CollCapacity(getInstance().length));
        for (Struct a: getInstance()) {
            Argument a_ = new Argument(a);
            args_.add(a_);
        }
        return args_;
    }

    public CustList<Struct> list() {
        CustList<Struct> args_ = new CustList<Struct>(new CollCapacity(getInstance().length));
        for (Struct a: getInstance()) {
            args_.add(Argument.getNull(a));
        }
        return args_;
    }
}
