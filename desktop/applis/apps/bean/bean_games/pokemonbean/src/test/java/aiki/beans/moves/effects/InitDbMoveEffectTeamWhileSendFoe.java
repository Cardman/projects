package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringMap;

public abstract class InitDbMoveEffectTeamWhileSendFoe extends InitDbMoveEffect {

    public static String callEffectTeamWhileSendFoeBeanClickStatus(NaSt _str, long... _args) {
        return navigateData(new EffectTeamWhileSendFoeBeanClickStatus(),_str,_args);
    }

    public static String callEffectTeamWhileSendFoeBeanClickStatusId(NaSt _str, long... _args) {
        callEffectTeamWhileSendFoeBeanClickStatus(_str, _args);
        return getValStatusId(_str);
    }
    public static NaSt callEffectTeamWhileSendFoeBeanDamageRateAgainstFoeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanDamageRateAgainstFoeGet(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanDeletedByFoeTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanDeletedByFoeTypesGet(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanGetTranslatedStatistic(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanGetTranslatedStatistic(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanGetTranslatedStatus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanGetTranslatedStatus(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanGetTranslatedType(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanGetTranslatedType(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanMapVarsDamageSentFoeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanMapVarsDamageSentFoeGet(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanMapVarsFailSendingGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanMapVarsFailSendingGet(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanReasonsSendingGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanReasonsSendingGet(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanStatisticsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanStatisticsGet(),_str,_args);
    }

    public static NaSt callEffectTeamWhileSendFoeBeanStatusByNbUsesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectTeamWhileSendFoeBeanStatusByNbUsesGet(),_str,_args);
    }
    protected static NaSt dispMoveEffTeamSend(FacadeGame _fac, int _index) {
        return dispMoveEffTeamSend(_fac, _index,0);
    }
    protected static NaSt dispMoveEffTeamSend(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectTeamSend(pk_);
        StringMap<String> mapping_ = mappingToEffectTeamSend();
        return transitEffect(_index,_indexEff,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectTeamSend(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_TEAMWHILESENDINGFOE,_pk.beanEffectTeamWhileSendFoeBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectTeamSend() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAMWHILESENDINGFOE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_TEAMWHILESENDINGFOE);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataDam() {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, eff());
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS, Rate.one());
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static EffectTeamWhileSendFoe eff() {
        EffectTeamWhileSendFoe e_ = Instances.newEffectTeamWhileSendFoe();
        e_.setFailSending(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.setDamageRateAgainstFoe(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.getDeletedByFoeTypes().add(T_TYPE1);
        e_.getStatistics().addEntry(Statistic.SPEED,1L);
        e_.getStatusByNbUses().addEntry(1L,S_STA_SIM);
        return e_;
    }
}
