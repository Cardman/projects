package aiki.beans.facade.simulation.dto;

import aiki.beans.*;
import code.bean.nat.*;
public class EvLineEvGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((EvLineStruct) _instance).getEvLine()).getEv().valueLong());
    }
}
