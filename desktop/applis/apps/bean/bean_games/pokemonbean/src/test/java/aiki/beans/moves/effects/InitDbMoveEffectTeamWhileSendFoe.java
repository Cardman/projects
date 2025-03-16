package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbMoveEffectTeamWhileSendFoe extends InitDbMoveEffect {

    public static String callEffectTeamWhileSendFoeBeanClickStatus(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.clickStatus(_args[0]);
    }

    public static String callEffectTeamWhileSendFoeBeanClickStatusId(EffectTeamWhileSendFoeBean _str, int... _args) {
        callEffectTeamWhileSendFoeBeanClickStatus(_str, _args);
        return getValStatusId(_str);
    }
    public static String callEffectTeamWhileSendFoeBeanDamageRateAgainstFoeGet(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getDamageRateAgainstFoe();
    }

    public static CustList<TranslatedKey> callEffectTeamWhileSendFoeBeanDeletedByFoeTypesGet(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getDeletedByFoeTypes();
    }

    public static String callEffectTeamWhileSendFoeBeanGetTranslatedStatistic(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getTranslatedStatistic(_args[0]);
    }

    public static String callEffectTeamWhileSendFoeBeanGetTranslatedStatus(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getTranslatedStatus(_args[0]);
    }

    public static String callEffectTeamWhileSendFoeBeanGetTranslatedType(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getTranslatedType(_args[0]);
    }

    public static AbsMap<String,String> callEffectTeamWhileSendFoeBeanMapVarsDamageSentFoeGet(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getMapVarsDamageSentFoe();
    }

    public static AbsMap<String,String> callEffectTeamWhileSendFoeBeanMapVarsFailSendingGet(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getMapVarsFailSending();
    }

    public static CustList<String> callEffectTeamWhileSendFoeBeanReasonsSendingGet(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getReasonsSending();
    }

    public static DictionaryComparator<TranslatedKey,Long> callEffectTeamWhileSendFoeBeanStatisticsGet(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getStatistics();
    }

    public static AbsMap<Long,TranslatedKey> callEffectTeamWhileSendFoeBeanStatusByNbUsesGet(EffectTeamWhileSendFoeBean _str, int... _args) {
        return _str.getStatusByNbUses();
    }
    protected static EffectTeamWhileSendFoeBean dispMoveEffTeamSend(FacadeGame _fac, int _index) {
        return dispMoveEffTeamSend(_fac, _index,0);
    }
    protected static EffectTeamWhileSendFoeBean dispMoveEffTeamSend(FacadeGame _fac, int _index, int _indexEff) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectTeamSend();
        return (EffectTeamWhileSendFoeBean)transitEffect(_index,_indexEff,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectTeamSend(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_TEAMWHILESENDINGFOE,_pk.beanEffectTeamWhileSendFoeBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectTeamSend() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFTEAMWHILESENDINGFOE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_TEAMWHILESENDINGFOE);
//        return map_;
//    }
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