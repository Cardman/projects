package aiki.beans.facade.simulation.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class RadioLineMoveIndexGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( (RadioLineMove) ((MvLineStruct) _instance).getWildPk()).getIndex());
    }
}
