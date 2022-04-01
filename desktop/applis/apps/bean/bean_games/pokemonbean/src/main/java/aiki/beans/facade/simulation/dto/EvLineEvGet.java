package aiki.beans.facade.simulation.dto;

import aiki.beans.EvLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class EvLineEvGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((EvLineStruct) _instance).getEvLine()).getEv());
    }
}
