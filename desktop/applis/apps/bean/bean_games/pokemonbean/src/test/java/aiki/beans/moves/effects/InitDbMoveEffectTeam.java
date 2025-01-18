package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
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
import code.scripts.confs.PkScriptPages;
import code.util.StringMap;

public abstract class InitDbMoveEffectTeam extends InitDbMoveEffect {

    public static NaSt callEffectTeamBeanCancelChgtStatFoeTeamGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanCancelChgtStatFoeTeamGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanCancelChgtStatTeamGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanCancelChgtStatTeamGet(),_str,_args);
    }

    public static String callEffectTeamBeanClickDisableFoeTeamEffects(NaSt _str, long... _args) {
        return navigateData(new EffectTeamBeanClickDisableFoeTeamEffects(),_str,_args);
    }
    public static String callEffectTeamBeanClickDisableFoeTeamEffectsId(NaSt _str, long... _args) {
        callEffectTeamBeanClickDisableFoeTeamEffects(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectTeamBeanClickDisableFoeTeamStatus(NaSt _str, long... _args) {
        return navigateData(new EffectTeamBeanClickDisableFoeTeamStatus(),_str,_args);
    }

    public static String callEffectTeamBeanClickDisableFoeTeamStatusId(NaSt _str, long... _args) {
        callEffectTeamBeanClickDisableFoeTeamStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callEffectTeamBeanClickStatus(NaSt _str, long... _args) {
        return navigateData(new EffectTeamBeanClickStatus(),_str,_args);
    }

    public static String callEffectTeamBeanClickStatusId(NaSt _str, long... _args) {
        callEffectTeamBeanClickStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callEffectTeamBeanClickUnusableMove(NaSt _str, long... _args) {
        return navigateData(new EffectTeamBeanClickUnusableMove(),_str,_args);
    }

    public static String callEffectTeamBeanClickUnusableMoveId(NaSt _str, long... _args) {
        callEffectTeamBeanClickUnusableMove(_str, _args);
        return getValMoveId(_str);
    }
    public static NaSt callEffectTeamBeanDefaultBoostGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanDefaultBoostGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanDisableFoeTeamEffectsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanDisableFoeTeamEffectsGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanDisableFoeTeamStatusGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanDisableFoeTeamStatusGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanForbiddenBoostGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanForbiddenBoostGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanForbiddingHealingGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanForbiddingHealingGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanGetTrDisableFoeTeamEffects(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanGetTrDisableFoeTeamEffects(),_str,_args);
    }

    public static NaSt callEffectTeamBeanGetTrDisableFoeTeamStatus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanGetTrDisableFoeTeamStatus(),_str,_args);
    }

    public static NaSt callEffectTeamBeanGetTrStatus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanGetTrStatus(),_str,_args);
    }

    public static NaSt callEffectTeamBeanGetTrUnusableMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanGetTrUnusableMove(),_str,_args);
    }

    public static NaSt callEffectTeamBeanMultDamageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanMultDamageGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanMultStatisticFoeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanMultStatisticFoeGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanMultStatisticGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanMultStatisticGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanProtectAgainstChGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanProtectAgainstChGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanProtectAgainstLowStatGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanProtectAgainstLowStatGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanProtectAgainstStatusGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanProtectAgainstStatusGet(),_str,_args);
    }

    public static NaSt callEffectTeamBeanUnusableMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamBeanUnusableMovesGet(),_str,_args);
    }
    protected static NaSt dispMoveEffTeam(FacadeGame _fac, int _index) {
        return dispMoveEffTeam(_fac, _index,0);
    }
    protected static NaSt dispMoveEffTeam(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectTeam(pk_);
        StringMap<String> mapping_ = mappingToEffectTeam();
        return transitEffect(_index,_indexEff,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectTeam(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_TEAM,_pk.beanEffectTeamBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectTeam() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAM_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_TEAM);
        return map_;
    }
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
