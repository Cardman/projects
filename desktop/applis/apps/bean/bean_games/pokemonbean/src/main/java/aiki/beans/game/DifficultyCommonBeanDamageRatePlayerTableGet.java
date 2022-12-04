package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;

public class DifficultyCommonBeanDamageRatePlayerTableGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getRateRate(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getDamageRatePlayerTable());
    }
}
