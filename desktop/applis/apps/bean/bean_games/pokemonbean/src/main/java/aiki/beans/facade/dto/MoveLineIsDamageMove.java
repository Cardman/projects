package aiki.beans.facade.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class MoveLineIsDamageMove implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( ((MvLineStruct) _instance).getWildPk()).isDamageMove());
    }
}
