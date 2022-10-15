package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.LgIntStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanGetRemainingLifeRate implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LgIntStruct(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getRemainingLifeRate(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
