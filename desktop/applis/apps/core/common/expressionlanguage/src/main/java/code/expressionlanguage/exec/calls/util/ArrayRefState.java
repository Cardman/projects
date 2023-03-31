package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;

public final class ArrayRefState {
    private final ArrayStruct array;
    private final int ref;
    private final boolean trueArr;

    public ArrayRefState(ArrayStruct _a, int _r, boolean _t) {
        this.array = _a;
        this.ref = _r;
        this.trueArr = _t;
    }

    public static ArrayRefState tryWrap(Struct _s,int _r) {
        ArrayRefState a_;
        if (_s instanceof ArrayStruct) {
            a_=new ArrayRefState((ArrayStruct) _s,_r,true);
        } else {
            a_=new ArrayRefState(new ArrayStruct(0,""),_r,false);
        }
        return a_;
    }
    public ArrayStruct getArray() {
        return array;
    }

    public int getRef() {
        return ref;
    }

    public boolean isFalseArr() {
        return !trueArr;
    }
}
