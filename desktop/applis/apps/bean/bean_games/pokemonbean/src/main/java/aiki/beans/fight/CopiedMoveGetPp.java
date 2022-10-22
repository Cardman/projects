package aiki.beans.fight;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class CopiedMoveGetPp implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((CopiedMoveStruct) _instance).getInstance()).getPp());
    }
}
