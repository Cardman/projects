package code.bean.nat;

import code.bean.nat.*;
import code.bean.nat.*;

public final class CstNatCaller implements NatCaller {

    private final String value;

    public CstNatCaller(String _v) {
        value = _v;
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return new NaStSt(value);
    }
}
