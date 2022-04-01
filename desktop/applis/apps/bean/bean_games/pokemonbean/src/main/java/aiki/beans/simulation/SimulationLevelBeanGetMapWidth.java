package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class SimulationLevelBeanGetMapWidth implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (SimulationLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getMapWidth());
    }
}
