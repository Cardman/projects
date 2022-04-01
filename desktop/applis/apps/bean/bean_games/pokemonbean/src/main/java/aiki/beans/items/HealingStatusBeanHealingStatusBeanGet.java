package aiki.beans.items;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class HealingStatusBeanHealingStatusBeanGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(HealingStatusBean.HEALING_STATUS_BEAN);
    }
}
