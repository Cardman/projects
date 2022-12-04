package aiki.beans.effects;

import aiki.beans.BeanPokemonCommonTs;
import code.bean.nat.*;

public abstract class InitDbMoveEffectCombo extends InitDbEffects {

    public static String callEffectComboBeanClickMove(int _combo, int _move) {
        return callEffectComboBeanClickMove(dispAllCombos(_combo),_move);
    }

    public static String callEffectComboBeanClickMove(NaSt _combo, int _move) {
        return navigateData(new EffectComboBeanClickMove(),_combo,toInt(callEffectComboBeanIndexGet(_combo)),_move);
    }

    public static String callEffectComboBeanClickMoveId(int _combo, int _move) {
        NaSt bean_ = dispAllCombos(_combo);
        callEffectComboBeanClickMove(bean_,_move);
        return getValMoveId(bean_);
    }

    public static NaSt callEffectComboBeanEndRoundGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanEndRoundGet(),dispAllCombos(_index));
    }

    public static NaSt callEffectComboBeanEndRoundRankGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanEndRoundRankGet(),dispAllCombos(_index));
    }

    public static NaSt callEffectComboBeanGetTrStatistic(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanGetTrStatistic(),dispAllCombos(_index),_args);
    }

    public static NaSt callEffectComboBeanIndexGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanIndexGet(),_str,_args);
    }

    public static NaSt callEffectComboBeanMapVarsFailEndRoundGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMapVarsFailEndRoundGet(),dispAllCombos(_index));
    }

    public static NaSt callEffectComboBeanMovesGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMovesGet(),dispAllCombos(_index));
    }

    public static NaSt callEffectComboBeanMultEvtRateSecEffGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMultEvtRateSecEffGet(),dispAllCombos(_index));
    }

    public static NaSt callEffectComboBeanMultStatisticFoeGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanMultStatisticFoeGet(),dispAllCombos(_index));
    }

    public static NaSt callEffectComboBeanRankIncrementNbRoundGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanRankIncrementNbRoundGet(),dispAllCombos(_index));
    }

    public static NaSt callEffectComboBeanReasonsEndRoundGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanReasonsEndRoundGet(),dispAllCombos(_index));
    }

    public static NaSt callEffectComboBeanRepeatedRoundsLawGet(int _index) {
        return BeanPokemonCommonTs.callLongs(new EffectComboBeanRepeatedRoundsLawGet(),dispAllCombos(_index));
    }

}
