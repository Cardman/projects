package aiki.beans.facade.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveLinePriorityGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((MvLineStruct) _instance).getWildPk()).getPriority());
    }
}
