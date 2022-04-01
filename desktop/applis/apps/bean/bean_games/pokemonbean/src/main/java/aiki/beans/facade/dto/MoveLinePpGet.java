package aiki.beans.facade.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class MoveLinePpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((MvLineStruct) _instance).getWildPk()).getPp());
    }
}
