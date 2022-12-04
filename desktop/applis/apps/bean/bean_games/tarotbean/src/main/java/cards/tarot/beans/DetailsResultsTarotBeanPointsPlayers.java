package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public final class DetailsResultsTarotBeanPointsPlayers implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return TarotStandards.getPointsPlayerVariantGameArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getPointsPlayers());
    }
}
