package aiki.beans.facade.fight;

import aiki.beans.fight.SufferedDamageCategoryStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class SufferedDamageCategoryGetUsing implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( ((SufferedDamageCategoryStruct) _instance).getSufferedDamageCategory()).getUsing());
    }
}
