package aiki.beans.effects;

import aiki.beans.BeanPokemonCommonTs;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbMoveEffectCombo extends InitDbEffects {

    public static String callEffectComboBeanClickMove(int _combo, int _move) {
        return callEffectComboBeanClickMove(dispAllCombos(_combo),_move);
    }

    public static String callEffectComboBeanClickMove(Struct _combo, int _move) {
        return navigateData(new EffectComboBeanClickMove(),_combo,toInt(callEffectComboBeanIndexGet(_combo)),_move);
    }

    public static String callEffectComboBeanClickMoveId(int _combo, int _move) {
        Struct bean_ = dispAllCombos(_combo);
        callEffectComboBeanClickMove(bean_,_move);
        return getValMoveId(bean_);
    }

    public static Struct callEffectComboBeanEndRoundGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanEndRoundGet(),dispAllCombos(_index));
    }

    public static Struct callEffectComboBeanEndRoundRankGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanEndRoundRankGet(),dispAllCombos(_index));
    }

    public static Struct callEffectComboBeanGetTrStatistic(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanGetTrStatistic(),dispAllCombos(_index),_args);
    }

    public static Struct callEffectComboBeanIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanIndexGet(),_str,_args);
    }

    public static Struct callEffectComboBeanMapVarsFailEndRoundGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMapVarsFailEndRoundGet(),dispAllCombos(_index));
    }

    public static Struct callEffectComboBeanMovesGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMovesGet(),dispAllCombos(_index));
    }

    public static Struct callEffectComboBeanMultEvtRateSecEffGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMultEvtRateSecEffGet(),dispAllCombos(_index));
    }

    public static Struct callEffectComboBeanMultStatisticFoeGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMultStatisticFoeGet(),dispAllCombos(_index));
    }

    public static Struct callEffectComboBeanRankIncrementNbRoundGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanRankIncrementNbRoundGet(),dispAllCombos(_index));
    }

    public static Struct callEffectComboBeanReasonsEndRoundGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanReasonsEndRoundGet(),dispAllCombos(_index));
    }

    public static Struct callEffectComboBeanRepeatedRoundsLawGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanRepeatedRoundsLawGet(),dispAllCombos(_index));
    }

}
