package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EffectWhileSendingBeanCopyBoostGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance()).getCopyBoost());
    }
}
