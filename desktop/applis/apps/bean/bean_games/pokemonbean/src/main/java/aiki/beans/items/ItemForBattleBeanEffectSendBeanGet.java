package aiki.beans.items;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class ItemForBattleBeanEffectSendBeanGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(ItemForBattleBean.EFFECT_SEND_BEAN);
    }
}
