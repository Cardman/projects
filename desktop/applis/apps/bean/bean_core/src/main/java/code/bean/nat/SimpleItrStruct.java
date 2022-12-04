package code.bean.nat;

import code.bean.nat.*;
import code.bean.nat.*;

public final class SimpleItrStruct extends NaNuSt {
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

    public NaSt next() {
        NaSt element_ = array.get(index);
        index++;
        return element_;
    }
}
