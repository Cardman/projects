package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
import code.util.core.*;

public class SimulationBeanPlaceFightSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getPlaceFight().valueInt(NumberUtil.parseInt(NaPa.getString(_args[0]).getInstance()));
        return NaNu.NULL_VALUE;
    }
}
