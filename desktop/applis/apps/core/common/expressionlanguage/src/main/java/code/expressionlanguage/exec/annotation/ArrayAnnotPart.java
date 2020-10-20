package code.expressionlanguage.exec.annotation;

import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;


final class ArrayAnnotPart extends ParentAnnotPart {

    private ArrayStruct array;

    void setArray(ArrayStruct _array) {
        array = _array;
    }

    @Override
    String getBegin() {
        return "{";
    }

    @Override
    String getEnd() {
        return "}";
    }

    @Override
    CustList<StackObject> getStack() {
        CustList<StackObject> elts_ = new CustList<StackObject>();
        for (Struct s: array.getInstance()) {
            StackArray st_ = new StackArray();
            st_.setValue(s);
            elts_.add(st_);
        }
        return elts_;
    }

    ArrayStruct getArray() {
        return array;
    }
}
