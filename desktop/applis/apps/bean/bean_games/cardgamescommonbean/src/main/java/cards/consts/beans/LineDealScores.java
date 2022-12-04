package cards.consts.beans;

import cards.consts.LineDeal;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;

public final class LineDealScores implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        LineDeal instance_ = ((LineDealStruct)_instance).getLineDeal();
        return BeanNatCommonLgNames.getLongArray(instance_.getScores());
    }
}
