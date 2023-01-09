package aiki.beans.facade.fight;

import aiki.beans.fight.KeyHypothesisStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;

public class KeyHypothesisGetDamageSecond implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( ((KeyHypothesisStruct) _instance).getKeyHypothesis()).getDamageSecond());
    }
}
