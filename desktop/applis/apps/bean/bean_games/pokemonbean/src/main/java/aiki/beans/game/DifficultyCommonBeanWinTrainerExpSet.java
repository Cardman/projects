package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public class DifficultyCommonBeanWinTrainerExpSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setWinTrainerExp(RateStruct.convertToRate(_args[0]));
        return NullStruct.NULL_VALUE;
    }
}
