package aiki.beans.facade.simulation.dto;

import aiki.beans.EvLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class EvLineEvSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( ((EvLineStruct) _instance).getEvLine()).setEv(NumParsers.convertToNumber(_args[0]).intStruct());
        return NullStruct.NULL_VALUE;
    }
}
