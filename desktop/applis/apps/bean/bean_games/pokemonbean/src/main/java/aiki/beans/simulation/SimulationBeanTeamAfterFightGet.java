package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanTeamAfterFightGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getPkPlayerArray(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getTeamAfterFight());
    }
}
