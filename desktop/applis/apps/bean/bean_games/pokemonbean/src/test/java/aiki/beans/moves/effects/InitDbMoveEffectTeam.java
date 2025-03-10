package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.CategoryMult;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectTeam extends InitDbMoveEffect {

    public static NaSt callEffectTeamBeanCancelChgtStatFoeTeamGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getCancelChgtStatFoeTeam());
    }

    public static NaSt callEffectTeamBeanCancelChgtStatTeamGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getCancelChgtStatTeam());
    }

    public static String callEffectTeamBeanClickDisableFoeTeamEffects(NaSt _str, int... _args) {
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickDisableFoeTeamEffects(_args[0])).getInstance();
    }
    public static String callEffectTeamBeanClickDisableFoeTeamEffectsId(NaSt _str, int... _args) {
        callEffectTeamBeanClickDisableFoeTeamEffects(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectTeamBeanClickDisableFoeTeamStatus(NaSt _str, int... _args) {
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickDisableFoeTeamStatus(_args[0])).getInstance();
    }

    public static String callEffectTeamBeanClickDisableFoeTeamStatusId(NaSt _str, int... _args) {
        callEffectTeamBeanClickDisableFoeTeamStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callEffectTeamBeanClickStatus(NaSt _str, int... _args) {
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickStatus(_args[0])).getInstance();
    }

    public static String callEffectTeamBeanClickStatusId(NaSt _str, int... _args) {
        callEffectTeamBeanClickStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callEffectTeamBeanClickUnusableMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickUnusableMove(_args[0])).getInstance();
    }

    public static String callEffectTeamBeanClickUnusableMoveId(NaSt _str, int... _args) {
        callEffectTeamBeanClickUnusableMove(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callEffectTeamBeanDefaultBoostGet(NaSt _str, int... _args) {
        return new NaNbSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getDefaultBoost());
    }

    public static NaSt callEffectTeamBeanDisableFoeTeamEffectsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getDisableFoeTeamEffects());
    }

    public static NaSt callEffectTeamBeanDisableFoeTeamStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getDisableFoeTeamStatus());
    }

    public static NaSt callEffectTeamBeanForbiddenBoostGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getForbiddenBoost());
    }

    public static NaSt callEffectTeamBeanForbiddingHealingGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getForbiddingHealing());
    }

    public static NaSt callEffectTeamBeanGetTrDisableFoeTeamEffects(NaSt _str, int... _args) {
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getTrDisableFoeTeamEffects(_args[0]));
    }

    public static NaSt callEffectTeamBeanGetTrDisableFoeTeamStatus(NaSt _str, int... _args) {
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getTrDisableFoeTeamStatus(_args[0]));
    }

    public static NaSt callEffectTeamBeanGetTrStatus(NaSt _str, int... _args) {
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getTrStatus(_args[0]));
    }

    public static NaSt callEffectTeamBeanGetTrUnusableMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getTrUnusableMove(_args[0]));
    }

    public static NaSt callEffectTeamBeanMultDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getCatMultRateMap(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getMultDamage());
    }

    public static NaSt callEffectTeamBeanMultStatisticFoeGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrRateVal(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getMultStatisticFoe());
    }

    public static NaSt callEffectTeamBeanMultStatisticGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrRateVal(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getMultStatistic());
    }

    public static NaSt callEffectTeamBeanProtectAgainstChGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getProtectAgainstCh());
    }

    public static NaSt callEffectTeamBeanProtectAgainstLowStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getProtectAgainstLowStat());
    }

    public static NaSt callEffectTeamBeanProtectAgainstStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getProtectAgainstStatus());
    }

    public static NaSt callEffectTeamBeanUnusableMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getUnusableMoves());
    }
    protected static NaSt dispMoveEffTeam(FacadeGame _fac, int _index) {
        return dispMoveEffTeam(_fac, _index,0);
    }
    protected static NaSt dispMoveEffTeam(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectTeam();
        return transitEffect(_index,_indexEff,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectTeam(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_TEAM,_pk.beanEffectTeamBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectTeam() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAM_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_TEAM);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataDam(EffectTeam _eff) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS,Rate.one());
        facade_.getData().completeVariables();
        return facade_;
    }

    protected static EffectTeam eff(boolean _forbiddingHealing, boolean _protectAgainstCh) {
        EffectTeam e_ = Instances.newEffectTeam();
        e_.setForbiddingHealing(_forbiddingHealing);
        e_.setProtectAgainstCh(_protectAgainstCh);
        e_.getMultStatistic().addEntry(Statistic.SPEED,Rate.one());
        e_.getMultStatisticFoe().addEntry(Statistic.SPEED,Rate.one());
        e_.getProtectAgainstLowStat().add(Statistic.SPEED);
        e_.getCancelChgtStatFoeTeam().add(Statistic.SPEED);
        e_.getForbiddenBoost().add(Statistic.SPEED);
        e_.getCancelChgtStatTeam().add(Statistic.SPEED);
        e_.getDisableFoeTeamEffects().add(M_STA);
        e_.getDisableFoeTeamStatus().add(S_STA_SIM);
        e_.getProtectAgainstStatus().add(S_STA_SIM);
        e_.getUnusableMoves().add(M_STA);
        e_.getMultDamage().addEntry(new CategoryMult(AUTRE,1),Rate.one());
        return e_;
    }
}