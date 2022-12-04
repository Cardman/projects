package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public final class DetailsResultsTarotBeanLinesDeclaring implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return TarotStandards.getSumDeclaringPlayerArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getLinesDeclaring());
    }
}
