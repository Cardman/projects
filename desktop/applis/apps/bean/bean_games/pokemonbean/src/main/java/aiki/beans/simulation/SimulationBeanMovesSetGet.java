package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanMovesSetGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getRdMvLine(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getMovesSet());
    }
}
