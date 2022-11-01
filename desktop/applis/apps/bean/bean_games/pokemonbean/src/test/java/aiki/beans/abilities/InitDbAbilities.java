package aiki.beans.abilities;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbAbilities extends InitDbConstr {

    public static Struct callAbilitiesBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanClickAbility(),_str,_args);
    }

    public static Struct callAbilitiesBeanGetTrAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanGetTrAbility(),_str,_args);
    }

    public static Struct callAbilitiesBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanSearch(),_str,_args);
    }

    public static Struct callAbilitiesBeanSortedAbilitiesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanSortedAbilitiesGet(),_str,_args);
    }

    public static Struct callAbilitiesBeanTypedAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanTypedAbilityGet(),_str,_args);
    }
    public static void fwdEffectWhileSendingWithStatistic(Struct _update, Struct _use) {
        callEffectWhileSendingBeanEffectSet(_update,callAbilityBeanGetEffectSending(_use));
    }
    public static Struct callAbilityBeanGetEffectSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
    }
    public static Struct callAbilitiesBeanTypedAbilitySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AbilitiesBeanTypedAbilitySet(),_str,_args);
    }
}
