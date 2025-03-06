package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanAllyChoiceSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getAllyChoice().setSelected(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
