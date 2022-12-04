package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanTeamAfterFightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getPkPlayerArray(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getTeamAfterFight());
    }
}
