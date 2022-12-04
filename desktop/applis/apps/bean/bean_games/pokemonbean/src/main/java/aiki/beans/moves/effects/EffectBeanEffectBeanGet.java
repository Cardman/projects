package aiki.beans.moves.effects;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectBeanEffectBeanGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(EffectBean.EFFECT_BEAN);
    }
}
