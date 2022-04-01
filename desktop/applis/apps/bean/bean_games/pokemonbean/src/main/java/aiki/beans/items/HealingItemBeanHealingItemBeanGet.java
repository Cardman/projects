package aiki.beans.items;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class HealingItemBeanHealingItemBeanGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(HealingItemBean.HEALING_ITEM_BEAN);
    }
}
