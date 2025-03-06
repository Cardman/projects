package aiki.beans.facade.simulation.dto;

import aiki.beans.*;
import code.bean.nat.*;
public class EvLineEvSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( ((EvLineStruct) _instance).getEvLine()).getEv().valueLong(NaPa.convertToNumber(_args[0]).longStruct());
        return NaNu.NULL_VALUE;
    }
}
