package aiki.beans.facade.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MoveLineIsDirect implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( ((MvLineStruct) _instance).getWildPk()).isDirect());
    }
}
