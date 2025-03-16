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
import aiki.beans.abilities.*;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbMoveEffectTeam extends InitDbMoveEffect {

    public static CustList<TranslatedKey> callEffectTeamBeanCancelChgtStatFoeTeamGet(EffectTeamBean _str, int... _args) {
        return _str.getCancelChgtStatFoeTeam();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanCancelChgtStatTeamGet(EffectTeamBean _str, int... _args) {
        return _str.getCancelChgtStatTeam();
    }

    public static String callEffectTeamBeanClickDisableFoeTeamEffects(EffectTeamBean _str, int... _args) {
        return _str.clickDisableFoeTeamEffects(_args[0]);
    }
    public static String callEffectTeamBeanClickDisableFoeTeamEffectsId(EffectTeamBean _str, int... _args) {
        callEffectTeamBeanClickDisableFoeTeamEffects(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectTeamBeanClickDisableFoeTeamStatus(EffectTeamBean _str, int... _args) {
        return _str.clickDisableFoeTeamStatus(_args[0]);
    }

    public static String callEffectTeamBeanClickDisableFoeTeamStatusId(EffectTeamBean _str, int... _args) {
        callEffectTeamBeanClickDisableFoeTeamStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callEffectTeamBeanClickStatus(EffectTeamBean _str, int... _args) {
        return _str.clickStatus(_args[0]);
    }

    public static String callEffectTeamBeanClickStatusId(EffectTeamBean _str, int... _args) {
        callEffectTeamBeanClickStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callEffectTeamBeanClickUnusableMove(EffectTeamBean _str, int... _args) {
        return _str.clickUnusableMove(_args[0]);
    }

    public static String callEffectTeamBeanClickUnusableMoveId(EffectTeamBean _str, int... _args) {
        callEffectTeamBeanClickUnusableMove(_str, _args);
        return getValMoveId(_str);
    }
    public static long callEffectTeamBeanDefaultBoostGet(EffectTeamBean _str, int... _args) {
        return _str.getDefaultBoost();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanDisableFoeTeamEffectsGet(EffectTeamBean _str, int... _args) {
        return _str.getDisableFoeTeamEffects();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanDisableFoeTeamStatusGet(EffectTeamBean _str, int... _args) {
        return _str.getDisableFoeTeamStatus();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanForbiddenBoostGet(EffectTeamBean _str, int... _args) {
        return _str.getForbiddenBoost();
    }

    public static boolean callEffectTeamBeanForbiddingHealingGet(EffectTeamBean _str, int... _args) {
        return _str.getForbiddingHealing();
    }

    public static String callEffectTeamBeanGetTrDisableFoeTeamEffects(EffectTeamBean _str, int... _args) {
        return _str.getTrDisableFoeTeamEffects(_args[0]);
    }

    public static String callEffectTeamBeanGetTrDisableFoeTeamStatus(EffectTeamBean _str, int... _args) {
        return _str.getTrDisableFoeTeamStatus(_args[0]);
    }

    public static String callEffectTeamBeanGetTrStatus(EffectTeamBean _str, int... _args) {
        return _str.getTrStatus(_args[0]);
    }

    public static String callEffectTeamBeanGetTrUnusableMove(EffectTeamBean _str, int... _args) {
        return _str.getTrUnusableMove(_args[0]);
    }

    public static AbsMap<TranslatedKeyPair,Rate> callEffectTeamBeanMultDamageGet(EffectTeamBean _str, int... _args) {
        return _str.getMultDamage();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectTeamBeanMultStatisticFoeGet(EffectTeamBean _str, int... _args) {
        return _str.getMultStatisticFoe();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectTeamBeanMultStatisticGet(EffectTeamBean _str, int... _args) {
        return _str.getMultStatistic();
    }

    public static boolean callEffectTeamBeanProtectAgainstChGet(EffectTeamBean _str, int... _args) {
        return _str.getProtectAgainstCh();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanProtectAgainstLowStatGet(EffectTeamBean _str, int... _args) {
        return _str.getProtectAgainstLowStat();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanProtectAgainstStatusGet(EffectTeamBean _str, int... _args) {
        return _str.getProtectAgainstStatus();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanUnusableMovesGet(EffectTeamBean _str, int... _args) {
        return _str.getUnusableMoves();
    }
    protected static EffectTeamBean dispMoveEffTeam(FacadeGame _fac, int _index) {
        return dispMoveEffTeam(_fac, _index,0);
    }
    protected static EffectTeamBean dispMoveEffTeam(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectTeam();
        return (EffectTeamBean)transitEffect(_index,_indexEff,pk_,all_);
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