package cards.belote.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public final class BeloteBeanGetScores implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getLongsArray(((BeloteBeanStruct)_instance).getInstance().scoresBelote());
    }
}
