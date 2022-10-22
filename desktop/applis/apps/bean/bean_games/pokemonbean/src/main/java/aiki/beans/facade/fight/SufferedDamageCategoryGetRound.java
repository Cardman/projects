package aiki.beans.facade.fight;

import aiki.beans.fight.SufferedDamageCategoryStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class SufferedDamageCategoryGetRound implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( ((SufferedDamageCategoryStruct) _instance).getInstance()).getRound());
    }
}
