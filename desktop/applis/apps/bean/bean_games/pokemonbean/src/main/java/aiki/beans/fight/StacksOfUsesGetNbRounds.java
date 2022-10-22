package aiki.beans.fight;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class StacksOfUsesGetNbRounds implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((StacksOfUsesStruct) _instance).getInstance()).getNbRounds());
    }
}
