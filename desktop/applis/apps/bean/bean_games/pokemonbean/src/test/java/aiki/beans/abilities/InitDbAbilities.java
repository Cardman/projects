package aiki.beans.abilities;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbAbilities extends InitDbConstr {
    public static void fwdEffectWhileSendingWithStatistic(Struct _update, Struct _use) {
        callEffectWhileSendingBeanEffectSet(_update,callAbilityBeanGetEffectSending(_use));
    }
    public static Struct callAbilityBeanGetEffectSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
    }
}
