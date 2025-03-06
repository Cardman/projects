package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanLevelEvoSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getLevelEvo().valueLong(NaPa.convertToNumber(_args[0]).longStruct());
        return NaNu.NULL_VALUE;
    }
}
