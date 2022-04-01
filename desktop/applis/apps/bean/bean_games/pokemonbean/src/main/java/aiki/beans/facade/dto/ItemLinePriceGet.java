package aiki.beans.facade.dto;

import aiki.beans.ItLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class ItemLinePriceGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((ItLineStruct) _instance).getWildPk()).getPrice());
    }
}
