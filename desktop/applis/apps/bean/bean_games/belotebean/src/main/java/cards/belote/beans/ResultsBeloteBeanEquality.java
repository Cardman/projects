package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public final class ResultsBeloteBeanEquality implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(((ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).equality());
    }
}
