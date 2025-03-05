package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;

public class SimulationLevelBeanNoFightSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationLevelBean) ((PokemonBeanStruct)_instance).getInstance()).noFight(NaPa.convertToNumber(_args[0]).longStruct());
        ( (SimulationLevelBean) ((PokemonBeanStruct)_instance).getInstance()).getNoFight().valueLong(NaPa.convertToNumber(_args[0]).longStruct());
        return NaNu.NULL_VALUE;
    }
}
