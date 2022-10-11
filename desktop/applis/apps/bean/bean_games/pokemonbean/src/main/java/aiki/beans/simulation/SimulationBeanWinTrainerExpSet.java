package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public class SimulationBeanWinTrainerExpSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getDifficultyCommon().setWinTrainerExp(RateStruct.convertToRate(_args[0]));
        return NullStruct.NULL_VALUE;
    }
}
