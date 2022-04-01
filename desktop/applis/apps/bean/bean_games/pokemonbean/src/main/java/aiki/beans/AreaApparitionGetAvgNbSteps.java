package aiki.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class AreaApparitionGetAvgNbSteps implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((AreaApparitionStruct) _instance).getWildPk()).getAvgNbSteps());
    }
}
