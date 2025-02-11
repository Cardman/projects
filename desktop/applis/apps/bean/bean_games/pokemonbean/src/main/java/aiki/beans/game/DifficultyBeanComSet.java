package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.simulation.SimulationBean;
import code.bean.nat.*;

public class DifficultyBeanComSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).setOwner((SimulationBean) ((PokemonBeanStruct)_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
