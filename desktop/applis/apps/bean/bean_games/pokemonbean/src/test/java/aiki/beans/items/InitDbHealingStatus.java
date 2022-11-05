package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbHealingStatus extends InitDbHealing {

    public static Struct callHealingStatusBeanClickStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanClickStatus(),_str,_args);
    }

    public static Struct callHealingStatusBeanGetTrStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanGetTrStatus(),_str,_args);
    }

    public static Struct callHealingStatusBeanHealedHpRateGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanHealedHpRateGet(),_str,_args);
    }

    public static Struct callHealingStatusBeanHealingKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanHealingKoGet(),_str,_args);
    }

    public static Struct callHealingStatusBeanHealingStatusBeanGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanHealingStatusBeanGet(),_str,_args);
    }

    public static Struct callHealingStatusBeanStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new HealingStatusBeanStatusGet(),_str,_args);
    }
}
