package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SimulationBeanGetImageAlly implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getImageAlly(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
