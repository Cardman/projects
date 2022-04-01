package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class BeloteSumDeclaringPlayerSum implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct((((BeloteSumDeclaringPlayerStruct)_instance).getSumDeclaringPlayer()).getSum());
    }
}
