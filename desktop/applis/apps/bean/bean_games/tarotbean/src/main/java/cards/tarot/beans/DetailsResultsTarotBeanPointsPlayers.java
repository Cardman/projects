package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class DetailsResultsTarotBeanPointsPlayers implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return TarotStandards.getPointsPlayerVariantGameArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getPointsPlayers());
    }
}
