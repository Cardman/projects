package aiki.beans.effects;

import aiki.beans.*;
import code.bean.nat.*;

public abstract class InitDbMoveEffectCombo extends InitDbEffects {

    public static String callEffectComboBeanClickMove(int _combo, int _move) {
        return callEffectComboBeanClickMove(dispAllCombos(_combo),_move);
    }

    public static String callEffectComboBeanClickMove(NaSt _combo, int _move) {
        return new NaStSt(( (EffectComboBean) ((PokemonBeanStruct)_combo).getInstance()).clickMove(_move)).getInstance();
    }

    public static String callEffectComboBeanClickMoveId(int _combo, int _move) {
        NaSt bean_ = dispAllCombos(_combo);
        callEffectComboBeanClickMove(bean_,_move);
        return getValMoveId(bean_);
    }

    public static NaSt callEffectComboBeanEndRoundGet(int _index) {
        return NaBoSt.of(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getEndRound());
    }

    public static NaSt callEffectComboBeanEndRoundRankGet(int _index) {
        return new NaNbSt(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getEndRoundRank());
    }

    public static NaSt callEffectComboBeanGetTrStatistic(int _index, int... _args) {
        return new NaStSt(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getTrStatistic(_args[0]));
    }
//
//    public static NaSt callEffectComboBeanIndexGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EffectComboBeanIndexGet(),_str,_args);
//    }

    public static NaSt callEffectComboBeanMapVarsFailEndRoundGet(int _index) {
        return PokemonStandards.getStrStr(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getMapVarsFailEndRound());
    }

    public static NaSt callEffectComboBeanMovesGet(int _index) {
        return PokemonStandards.getValues(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getMoves());
    }

    public static NaSt callEffectComboBeanMultEvtRateSecEffGet(int _index) {
        return new RtSt(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getMultEvtRateSecEff());
    }

    public static NaSt callEffectComboBeanMultStatisticFoeGet(int _index) {
        return PokemonStandards.getStaRate(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getMultStatisticFoe());
    }

    public static NaSt callEffectComboBeanRankIncrementNbRoundGet(int _index) {
        return new NaNbSt(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getRankIncrementNbRound());
    }

    public static NaSt callEffectComboBeanReasonsEndRoundGet(int _index) {
        return BeanNatCommonLgNames.getStringArray(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getReasonsEndRound());
    }

    public static NaSt callEffectComboBeanRepeatedRoundsLawGet(int _index) {
        return PokemonStandards.getLgIntRate(( (EffectComboBean) ((PokemonBeanStruct)dispAllCombos(_index)).getInstance()).getRepeatedRoundsLaw());
    }

}