package code.bean.nat;

import code.bean.nat.*;
import code.bean.nat.*;

public final class PairStruct extends NaNuSt {
    private final NaSt first;
    private final NaSt second;

    public PairStruct(NaSt _first, NaSt _second) {
        this.first = _first;
        this.second = _second;
    }

    public NaSt getFirst() {
        return first;
    }

    public NaSt getSecond() {
        return second;
    }
}
