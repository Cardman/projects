package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.bean.nat.*;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbMoveEffectStatistic extends InitDbMoveEffect {
    protected static final String ST_ACC_TR="ST_ACC_TR";
    public static CustList<TranslatedKey> callEffectStatisticBeanCancelChgtStatGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getCancelChgtStat();
    }

    public static CustList<TranslatedKey> callEffectStatisticBeanCancelLowStatGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getCancelLowStat();
    }

    public static CustList<TranslatedKey> callEffectStatisticBeanCopyBoostGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getCopyBoost();
    }

    public static long callEffectStatisticBeanDefaultBoostGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getDefaultBoost();
    }

    public static Rate callEffectStatisticBeanEvtRateGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getEvtRate();
    }

    public static String callEffectStatisticBeanEvtRatePerCentGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getEvtRatePerCent();
    }

    public static String callEffectStatisticBeanGetFail(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getFail(_args[0]);
    }

    public static Rate callEffectStatisticBeanGetRate(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getRate(_args[0]);
    }

    public static String callEffectStatisticBeanGetSwapFail(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getSwapFail(_args[0]);
    }

    public static boolean callEffectStatisticBeanIsAlwaysEnabled(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().isAlwaysEnabled();
    }

    public static AbsMap<String,String> callEffectStatisticBeanMapVarsStatisticsGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getMapVarsStatistics();
    }

    public static boolean callEffectStatisticBeanNotEmptyVarBoost(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().notEmptyVarBoost();
    }

    public static boolean callEffectStatisticBeanRandomStatis(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().randomStatis();
    }

    public static DictionaryComparator<TranslatedKey,StatRankRate> callEffectStatisticBeanStatisVarRankGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getStatisVarRank();
    }

    public static CustList<TranslatedKey> callEffectStatisticBeanSwapBoostStatisGet(EffectStatisticBean _str, int... _args) {
        return _str.getEffectStatisticCommon().getSwapBoostStatis().getKeys();
    }

    protected static FacadeGame feedDbMoveEffDataDamComp(EffectStatistic _eff) {
        FacadeGame f_ = feedDbMoveEffDataDam(_eff);
        f_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        return f_;
    }
    protected static EffectStatisticBean dispMoveEffStatis(FacadeGame _fac, int _index) {
        return dispMoveEffStatis(_fac, _index,0);
    }
    protected static EffectStatisticBean dispMoveEffStatis(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectStatis();
        return (EffectStatisticBean)transitEffect(_index,_indexEff,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectStatis(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_STATIS,_pk.beanEffectStatisticBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectStatis() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSTATIS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_STATIS);
//        return map_;
//    }
    private static FacadeGame feedDbMoveEffDataDam(EffectStatistic _eff) {
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
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ACCURACY,ST_ACC_TR);
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static EffectStatistic eff(Rate _evt, String _failStat, String _swapFail) {
        EffectStatistic e_ = Instances.newEffectStatistic();
        e_.setEvtRate(_evt);
        e_.getCancelLowStat().add(Statistic.SPEED);
        e_.getCancelChgtStat().add(Statistic.SPEED);
        e_.getCopyBoost().add(Statistic.SPEED);
        e_.getSwapBoostStatis().add(Statistic.SPEED);
        e_.getLocalFailStatis().addEntry(Statistic.SPEED, _failStat);
        e_.getLocalFailSwapBoostStatis().addEntry(Statistic.SPEED, _swapFail);
        return e_;
    }
    protected EffectStatistic withStatisVarRank(EffectStatistic _e, Statistic _statis, long _v) {
        _e.getStatisVarRank().addEntry(_statis,_v);
        return _e;
    }
    protected EffectStatistic withLawBoost(EffectStatistic _e, Statistic _statis, LgInt _v) {
        _e.getLawBoost().addQuickEvent(_statis,_v);
        return _e;
    }
}