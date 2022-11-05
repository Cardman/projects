package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbHealingHp extends InitDbHealing {

    public static Struct callHealingHpBeanHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingHpBeanHpGet(),_str,_args);
    }
}
