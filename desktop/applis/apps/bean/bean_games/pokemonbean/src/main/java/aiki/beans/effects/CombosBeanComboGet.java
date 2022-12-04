package aiki.beans.effects;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class CombosBeanComboGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(CombosBean.COMBO);
    }
}
