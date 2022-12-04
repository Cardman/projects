package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;

public class DifficultyCommonBeanWinTrainerExpGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getWinTrainerExp());
    }
}
