package aiki.beans.db;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.InitDbBean;
import aiki.beans.effects.EffectWhileSendingBeanEffectSet;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbConstr extends InitDbBean {

    public static Struct callEffectWhileSendingBeanEffectSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new EffectWhileSendingBeanEffectSet(),_str,_args);
    }
}
