package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public final class ResultsBeloteBeanPointsDefenseDefinitif implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(((ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getPointsDefenseDefinitif());
    }
}
