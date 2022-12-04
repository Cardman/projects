package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class SimulationLevelBeanNoFightSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationLevelBean) ((PokemonBeanStruct)_instance).getInstance()).setNoFight(NaPa.convertToNumber(_args[0]).intStruct());
        return NaNu.NULL_VALUE;
    }
}
