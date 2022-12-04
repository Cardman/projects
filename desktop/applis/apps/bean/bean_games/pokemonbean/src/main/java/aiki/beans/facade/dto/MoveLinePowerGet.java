package aiki.beans.facade.dto;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class MoveLinePowerGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((MvLineStruct) _instance).getWildPk()).getPower());
    }
}
