package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;

public class DifficultyCommonBeanWinTrainerExpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getWinTrainerExp());
    }
}
