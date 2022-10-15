package code.bean.nat;

import code.expressionlanguage.structs.AbNullStruct;
import code.expressionlanguage.structs.Struct;

public final class SimpleItrStruct extends AbNullStruct {
    private final NatArrayStruct array;
    private int index;
    private final int length;

    public SimpleItrStruct(NatArrayStruct _array) {
        array = _array;
        length = _array.getLength();
    }

    public boolean hasNext() {
        return index < length;
    }

    public Struct next() {
        Struct element_ = array.get(index);
        index++;
        return element_;
    }
}
