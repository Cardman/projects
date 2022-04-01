package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanGetLevel implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getLevel(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
