package code.bean.nat;

import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;

public final class SimpleItrStruct extends CommNatStruct {
    private final ArrayStruct array;
    private int index;
    private final int length;

    public SimpleItrStruct(String _className, ArrayStruct _array) {
        super(_className);
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
