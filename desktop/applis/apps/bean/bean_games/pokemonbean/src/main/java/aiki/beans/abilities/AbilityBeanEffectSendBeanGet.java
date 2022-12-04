package aiki.beans.abilities;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanEffectSendBeanGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(AbilityBean.EFFECT_SEND_BEAN);
    }
}
