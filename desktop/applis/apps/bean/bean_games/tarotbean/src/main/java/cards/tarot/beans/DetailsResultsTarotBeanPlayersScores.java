package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class DetailsResultsTarotBeanPlayersScores implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return TarotStandards.getScoresPlayersArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getPlayersScores());
    }
}
