package aiki.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class AnticipationGetNbRounds implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( ((AnticipationStruct) _instance).getInstance()).getNbRounds());
    }
}
