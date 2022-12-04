package aiki.beans.facade.simulation.dto;

import aiki.beans.EvLineStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EvLineEvSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( ((EvLineStruct) _instance).getEvLine()).setEv(NaPa.convertToNumber(_args[0]).intStruct());
        return NaNu.NULL_VALUE;
    }
}
