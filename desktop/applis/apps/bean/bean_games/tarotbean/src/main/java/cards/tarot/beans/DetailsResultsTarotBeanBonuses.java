package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public final class DetailsResultsTarotBeanBonuses implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return TarotStandards.getBonusesPlayersArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getBonuses());
    }
}
