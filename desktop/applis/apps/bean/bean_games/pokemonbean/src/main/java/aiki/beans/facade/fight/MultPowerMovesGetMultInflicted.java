package aiki.beans.facade.fight;

import aiki.beans.fight.MultPowerMovesStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class MultPowerMovesGetMultInflicted implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( ((MultPowerMovesStruct) _instance).getInstance()).getMultInflicted());
    }
}
