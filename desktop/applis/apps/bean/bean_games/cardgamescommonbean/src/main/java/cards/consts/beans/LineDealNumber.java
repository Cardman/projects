package cards.consts.beans;

import cards.consts.LineDeal;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public final class LineDealNumber implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        LineDeal instance_ = ((LineDealStruct)_instance).getLineDeal();
        return new NaNbSt(instance_.getNumber());
    }
}
