package aiki.beans.effects;

import aiki.beans.*;
import code.bean.nat.*;
import code.maths.*;
import code.util.*;
public abstract class InitDbMoveEffectCombo extends InitDbEffects {

    public static String callEffectComboBeanClickMove(int _combo, int _move) {
        return callEffectComboBeanClickMove(dispAllCombos(_combo),_move);
    }

    public static String callEffectComboBeanClickMove(NaSt _combo, int _move) {
        return ( (EffectComboBean) ((PokemonBeanStruct)_combo).getInstance()).clickMove(_move);
    }

    public static String callEffectComboBeanClickMoveId(int _combo, int _move) {
        NaSt bean_ = dispAllCombos(_combo);
        callEffectComboBeanClickMove(bean_,_move);
        return getValMoveId(bean_);
    }

    public static boolean callEffectComboBeanEndRoundGet(int _index) {
        return ( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getEndRound();
    }

    public static long callEffectComboBeanEndRoundRankGet(int _index) {
        return ( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getEndRoundRank();
    }

    public static String callEffectComboBeanGetTrStatistic(int _index, int... _args) {
        return ( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getTrStatistic(_args[0]);
    }
//
//    public static NaSt callEffectComboBeanIndexGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EffectComboBeanIndexGet(),_str,_args);
//    }

    public static AbsMap<String,String> callEffectComboBeanMapVarsFailEndRoundGet(int _index) {
        return (( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getMapVarsFailEndRound());
    }

    public static CustList<TranslatedKey> callEffectComboBeanMovesGet(int _index) {
        return (( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getMoves());
    }

    public static Rate callEffectComboBeanMultEvtRateSecEffGet(int _index) {
        return ( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getMultEvtRateSecEff();
    }

    public static AbsMap<TranslatedKey,Rate> callEffectComboBeanMultStatisticFoeGet(int _index) {
        return (( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getMultStatisticFoe());
    }

    public static long callEffectComboBeanRankIncrementNbRoundGet(int _index) {
        return ( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getRankIncrementNbRound();
    }

    public static CustList<String> callEffectComboBeanReasonsEndRoundGet(int _index) {
        return (( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getReasonsEndRound());
    }

    public static AbsMap<LgInt,Rate> callEffectComboBeanRepeatedRoundsLawGet(int _index) {
        return (( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getRepeatedRoundsLaw());
    }

}