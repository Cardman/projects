package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class AnticipationGetDamage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( ((AnticipationStruct) _instance).getAnticipation()).getDamage());
    }
}
