package aiki.beans.abilities;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class AbilityBeanEffectSendBeanGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(AbilityBean.EFFECT_SEND_BEAN);
    }
}
