package aiki.beans.facade.fight;

import aiki.beans.fight.KeyHypothesisStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class KeyHypothesisGetNumberTarget implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(( ((KeyHypothesisStruct) _instance).getKeyHypothesis()).getNumberTarget());
    }
}
