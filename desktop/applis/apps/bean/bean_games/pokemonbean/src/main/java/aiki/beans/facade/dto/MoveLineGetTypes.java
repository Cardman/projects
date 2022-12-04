package aiki.beans.facade.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveLineGetTypes implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( ((MvLineStruct) _instance).getWildPk()).getTypes());
    }
}
