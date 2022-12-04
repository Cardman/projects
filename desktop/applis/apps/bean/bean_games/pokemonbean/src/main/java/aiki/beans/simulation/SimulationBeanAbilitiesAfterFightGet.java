package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class SimulationBeanAbilitiesAfterFightGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getAbilitiesAfterFight());
    }
}
