package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbMoveEffectRestriction extends InitDbMoveEffect {

    public static Struct callEffectRestrictionBeanForbid(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbid(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidLastMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidLastMove(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidStatusMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidStatusMove(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidTargetUsingItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidTargetUsingItemGet(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidUseMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidUseMove(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidUserMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidUserMoves(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForceUseMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForceUseMove(),_str,_args);
    }
}
