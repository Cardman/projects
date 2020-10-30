package code.formathtml.nat;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public class SimpleItrStruct extends WithoutParentIdStruct {
    private final String className;
    private final ArrayStruct array;
    private int index;
    private int length;

    public SimpleItrStruct(String _className, ArrayStruct _array) {
        className = _className;
        array = _array;
        length = _array.getLength();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
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
