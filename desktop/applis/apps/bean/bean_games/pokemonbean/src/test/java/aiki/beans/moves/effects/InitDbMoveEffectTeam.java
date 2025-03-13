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

    public static CustList<TranslatedKey> callEffectTeamBeanCancelChgtStatFoeTeamGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getCancelChgtStatFoeTeam());
    }

    public static CustList<TranslatedKey> callEffectTeamBeanCancelChgtStatTeamGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getCancelChgtStatTeam());
    }

    public static String callEffectTeamBeanClickDisableFoeTeamEffects(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickDisableFoeTeamEffects(_args[0]);
    }
    public static String callEffectTeamBeanClickDisableFoeTeamEffectsId(NaSt _str, int... _args) {
        callEffectTeamBeanClickDisableFoeTeamEffects(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectTeamBeanClickDisableFoeTeamStatus(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickDisableFoeTeamStatus(_args[0]);
    }

    public static String callEffectTeamBeanClickDisableFoeTeamStatusId(NaSt _str, int... _args) {
        callEffectTeamBeanClickDisableFoeTeamStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callEffectTeamBeanClickStatus(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickStatus(_args[0]);
    }

    public static String callEffectTeamBeanClickStatusId(NaSt _str, int... _args) {
        callEffectTeamBeanClickStatus(_str, _args);
        return getValStatusId(_str);
    }

    public static String callEffectTeamBeanClickUnusableMove(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickUnusableMove(_args[0]);
    }

    public static String callEffectTeamBeanClickUnusableMoveId(NaSt _str, int... _args) {
        callEffectTeamBeanClickUnusableMove(_str, _args);
        return getValMoveId(_str);
    }
    public static long callEffectTeamBeanDefaultBoostGet(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getDefaultBoost();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanDisableFoeTeamEffectsGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getDisableFoeTeamEffects());
    }

    public static CustList<TranslatedKey> callEffectTeamBeanDisableFoeTeamStatusGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getDisableFoeTeamStatus());
    }

    public static CustList<TranslatedKey> callEffectTeamBeanForbiddenBoostGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getForbiddenBoost());
    }

    public static boolean callEffectTeamBeanForbiddingHealingGet(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getForbiddingHealing();
    }

    public static String callEffectTeamBeanGetTrDisableFoeTeamEffects(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getTrDisableFoeTeamEffects(_args[0]);
    }

    public static String callEffectTeamBeanGetTrDisableFoeTeamStatus(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getTrDisableFoeTeamStatus(_args[0]);
    }

    public static String callEffectTeamBeanGetTrStatus(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getTrStatus(_args[0]);
    }

    public static String callEffectTeamBeanGetTrUnusableMove(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getTrUnusableMove(_args[0]);
    }

    public static AbsMap<TranslatedKeyPair,Rate> callEffectTeamBeanMultDamageGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getMultDamage());
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectTeamBeanMultStatisticFoeGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getMultStatisticFoe());
    }

    public static DictionaryComparator<TranslatedKey,Rate> callEffectTeamBeanMultStatisticGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getMultStatistic());
    }

    public static boolean callEffectTeamBeanProtectAgainstChGet(NaSt _str, int... _args) {
        return ( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getProtectAgainstCh();
    }

    public static CustList<TranslatedKey> callEffectTeamBeanProtectAgainstLowStatGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getProtectAgainstLowStat());
    }

    public static CustList<TranslatedKey> callEffectTeamBeanProtectAgainstStatusGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getProtectAgainstStatus());
    }

    public static CustList<TranslatedKey> callEffectTeamBeanUnusableMovesGet(NaSt _str, int... _args) {
        return (( (EffectTeamBean) ((PokemonBeanStruct)_str).getInstance()).getUnusableMoves());
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