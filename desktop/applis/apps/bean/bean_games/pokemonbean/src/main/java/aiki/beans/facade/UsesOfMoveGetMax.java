package aiki.beans.facade;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class UsesOfMoveGetMax implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((UsesOfMoveStruct) _instance).getInstance()).getMax());
    }
}
