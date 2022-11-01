package aiki.beans.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public final class InitDbMoveEffectCombo extends InitDbConstr {

    public static Struct callEffectComboBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanClickMove(),_str,_args);
    }

    public static Struct callEffectComboBeanEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanEndRoundGet(),_str,_args);
    }

    public static Struct callEffectComboBeanEndRoundRankGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanEndRoundRankGet(),_str,_args);
    }

    public static Struct callEffectComboBeanGetTrStatistic(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanGetTrStatistic(),_str,_args);
    }

    public static Struct callEffectComboBeanIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanIndexGet(),_str,_args);
    }

    public static Struct callEffectComboBeanMapVarsFailEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMapVarsFailEndRoundGet(),_str,_args);
    }

    public static Struct callEffectComboBeanMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMovesGet(),_str,_args);
    }

    public static Struct callEffectComboBeanMultEvtRateSecEffGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMultEvtRateSecEffGet(),_str,_args);
    }

    public static Struct callEffectComboBeanMultStatisticFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMultStatisticFoeGet(),_str,_args);
    }

    public static Struct callEffectComboBeanRankIncrementNbRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanRankIncrementNbRoundGet(),_str,_args);
    }

    public static Struct callEffectComboBeanReasonsEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanReasonsEndRoundGet(),_str,_args);
    }

    public static Struct callEffectComboBeanRepeatedRoundsLawGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanRepeatedRoundsLawGet(),_str,_args);
    }

    public static Struct callEffectComboBeanIndexSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EffectComboBeanIndexSet(),_str,_args);
    }
    public static Struct callEffectComboBeanCombosSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new EffectComboBeanCombosSet(),_str,_args);
    }
}
