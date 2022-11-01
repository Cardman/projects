package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbMoveEffectStatus extends InitDbMoveEffect {

    public static Struct callEffectStatusBeanClickLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanClickLink(),_str,_args);
    }

    public static Struct callEffectStatusBeanClickLinkDeleted(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanClickLinkDeleted(),_str,_args);
    }

    public static Struct callEffectStatusBeanDeletedStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanDeletedStatusGet(),_str,_args);
    }

    public static Struct callEffectStatusBeanGetFail(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanGetFail(),_str,_args);
    }

    public static Struct callEffectStatusBeanGetTrLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanGetTrLink(),_str,_args);
    }

    public static Struct callEffectStatusBeanGetTrLinkDeleted(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanGetTrLinkDeleted(),_str,_args);
    }

    public static Struct callEffectStatusBeanIsStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanIsStatus(),_str,_args);
    }

    public static Struct callEffectStatusBeanKoUserHealSubstGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanKoUserHealSubstGet(),_str,_args);
    }

    public static Struct callEffectStatusBeanLawStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanLawStatusGet(),_str,_args);
    }

    public static Struct callEffectStatusBeanMapVarsStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanMapVarsStatusGet(),_str,_args);
    }

    public static Struct callEffectStatusBeanStatusFromUserGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanStatusFromUserGet(),_str,_args);
    }
}
