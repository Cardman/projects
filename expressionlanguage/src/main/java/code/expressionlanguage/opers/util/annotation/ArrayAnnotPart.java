package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;


final class ArrayAnnotPart extends ParentAnnotPart {

    private ArrayStruct array;

    public ArrayStruct getArray() {
        return array;
    }

    public void setArray(ArrayStruct _array) {
        array = _array;
    }

    @Override
    ArrayStruct getInstance() {
        return array;
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

}
