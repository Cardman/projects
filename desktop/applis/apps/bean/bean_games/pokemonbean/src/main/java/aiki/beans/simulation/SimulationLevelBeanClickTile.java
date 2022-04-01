package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class SimulationLevelBeanClickTile implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (SimulationLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickTile(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
