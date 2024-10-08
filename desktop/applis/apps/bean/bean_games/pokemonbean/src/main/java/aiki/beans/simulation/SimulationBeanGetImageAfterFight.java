package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanGetImageAfterFight implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getImageAfterFight(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
