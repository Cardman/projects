package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public final class DetailsResultsTarotBeanPlayersScores implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return TarotStandards.getScoresPlayersArray(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getPlayersScores());
    }
}
