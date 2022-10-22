package aiki.beans.fight;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class ActivityOfMoveGetNbTurn implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((ActivityOfMoveStruct) _instance).getInstance()).getNbTurn());
    }
}
