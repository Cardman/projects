package aiki.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class StacksOfUsesIsLastStacked implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( ((StacksOfUsesStruct) _instance).getInstance()).isLastStacked());
    }
}
