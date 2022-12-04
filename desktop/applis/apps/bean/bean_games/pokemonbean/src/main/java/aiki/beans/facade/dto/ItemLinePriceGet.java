package aiki.beans.facade.dto;

import aiki.beans.ItLineStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemLinePriceGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((ItLineStruct) _instance).getWildPk()).getPrice());
    }
}
