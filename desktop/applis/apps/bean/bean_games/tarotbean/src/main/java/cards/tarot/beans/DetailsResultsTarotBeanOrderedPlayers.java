package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public final class DetailsResultsTarotBeanOrderedPlayers implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return TarotStandards.getRankingPlayerVariantGameArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getOrderedPlayers());
    }
}
