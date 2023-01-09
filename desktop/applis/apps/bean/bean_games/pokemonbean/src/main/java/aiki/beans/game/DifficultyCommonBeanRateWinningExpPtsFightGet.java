package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;

public class DifficultyCommonBeanRateWinningExpPtsFightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getRateWinningExpPtsFight());
    }
}
