package cards.tarot.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public final class TarotBeanGetScores implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getLongsArray(((TarotBeanStruct)_instance).getInstance().scoresTarot());
    }
}
