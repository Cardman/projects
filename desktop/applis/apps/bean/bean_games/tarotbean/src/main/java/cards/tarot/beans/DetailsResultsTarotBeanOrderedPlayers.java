package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public final class DetailsResultsTarotBeanOrderedPlayers implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return TarotStandards.getRankingPlayerVariantGameArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getOrderedPlayers());
    }
}
