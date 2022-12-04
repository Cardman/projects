package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public class AnticipationGetDamage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct(( ((AnticipationStruct) _instance).getInstance()).getDamage());
    }
}
