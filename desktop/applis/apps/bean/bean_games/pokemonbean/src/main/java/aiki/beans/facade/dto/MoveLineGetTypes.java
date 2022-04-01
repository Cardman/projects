package aiki.beans.facade.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MoveLineGetTypes implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( ((MvLineStruct) _instance).getWildPk()).getTypes());
    }
}
