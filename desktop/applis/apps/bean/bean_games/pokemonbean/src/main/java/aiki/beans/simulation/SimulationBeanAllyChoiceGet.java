package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanAllyChoiceGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getAllyChoice().isSelected());
    }
}
