package aiki.beans.effects;

import aiki.beans.*;
import code.maths.*;
import code.util.*;
public abstract class InitDbMoveEffectCombo extends InitDbEffects {

    public static String callEffectComboBeanClickMove(int _combo, int _move) {
        return callEffectComboBeanClickMove(dispAllCombos(_combo),_move);
    }

    public static String callEffectComboBeanClickMove(EffectComboBean _combo, int _move) {
        return _combo.clickMove(_move);
    }

    public static String callEffectComboBeanClickMoveId(int _combo, int _move) {
        EffectComboBean bean_ = dispAllCombos(_combo);
        callEffectComboBeanClickMove(bean_,_move);
        return getValMoveId(bean_);
    }

    public static boolean callEffectComboBeanEndRoundGet(int _index) {
        return dispAllCombos(_index).getEndRound();
    }

    public static long callEffectComboBeanEndRoundRankGet(int _index) {
        return dispAllCombos(_index).getEndRoundRank();
    }

    public static String callEffectComboBeanGetTrStatistic(int _index, int... _args) {
        return dispAllCombos(_index).getTrStatistic(_args[0]);
    }
//
//    public static NaSt callEffectComboBeanIndexGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EffectComboBeanIndexGet(),_str,_args);
//    }

    public static AbsMap<String,String> callEffectComboBeanMapVarsFailEndRoundGet(int _index) {
        return dispAllCombos(_index).getMapVarsFailEndRound();
    }

    public static CustList<TranslatedKey> callEffectComboBeanMovesGet(int _index) {
        return dispAllCombos(_index).getMoves();
    }

    public static Rate callEffectComboBeanMultEvtRateSecEffGet(int _index) {
        return dispAllCombos(_index).getMultEvtRateSecEff();
    }

    public static AbsMap<TranslatedKey,Rate> callEffectComboBeanMultStatisticFoeGet(int _index) {
        return dispAllCombos(_index).getMultStatisticFoe();
    }

    public static long callEffectComboBeanRankIncrementNbRoundGet(int _index) {
        return dispAllCombos(_index).getRankIncrementNbRound();
    }

    public static CustList<String> callEffectComboBeanReasonsEndRoundGet(int _index) {
        return dispAllCombos(_index).getReasonsEndRound();
    }

    public static AbsMap<LgInt,Rate> callEffectComboBeanRepeatedRoundsLawGet(int _index) {
        return dispAllCombos(_index).getRepeatedRoundsLaw();
    }

}