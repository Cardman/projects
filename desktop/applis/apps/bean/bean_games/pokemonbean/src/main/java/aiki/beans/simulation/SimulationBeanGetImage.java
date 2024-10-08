package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanGetImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getImage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
