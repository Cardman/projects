package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class DetailsResultsTarotBeanBonuses implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return TarotStandards.getBonusesPlayersArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getBonuses());
    }
}
