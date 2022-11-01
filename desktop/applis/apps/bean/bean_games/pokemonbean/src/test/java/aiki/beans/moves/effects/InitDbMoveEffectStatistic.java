package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectStatistic extends InitDbMoveEffect {
    protected static final String ST_ACC_TR="ST_ACC_TR";
    public static Struct callEffectStatisticBeanCancelChgtStatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanCancelChgtStatGet(),_str,_args);
    }

    public static Struct callEffectStatisticBeanCancelLowStatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanCancelLowStatGet(),_str,_args);
    }

    public static Struct callEffectStatisticBeanCopyBoostGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanCopyBoostGet(),_str,_args);
    }

    public static Struct callEffectStatisticBeanDefaultBoostGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanDefaultBoostGet(),_str,_args);
    }

    public static Struct callEffectStatisticBeanEvtRateGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanEvtRateGet(),_str,_args);
    }

    public static Struct callEffectStatisticBeanEvtRatePerCentGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanEvtRatePerCentGet(),_str,_args);
    }

    public static Struct callEffectStatisticBeanGetFail(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanGetFail(),_str,_args);
    }

    public static Struct callEffectStatisticBeanGetRate(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanGetRate(),_str,_args);
    }

    public static Struct callEffectStatisticBeanGetSwapFail(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanGetSwapFail(),_str,_args);
    }

    public static Struct callEffectStatisticBeanIsAlwaysEnabled(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanIsAlwaysEnabled(),_str,_args);
    }

    public static Struct callEffectStatisticBeanMapVarsStatisticsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanMapVarsStatisticsGet(),_str,_args);
    }

    public static Struct callEffectStatisticBeanNotEmptyVarBoost(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanNotEmptyVarBoost(),_str,_args);
    }

    public static Struct callEffectStatisticBeanRandomStatis(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanRandomStatis(),_str,_args);
    }

    public static Struct callEffectStatisticBeanStatisVarRankGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanStatisVarRankGet(),_str,_args);
    }

    public static Struct callEffectStatisticBeanSwapBoostStatisGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatisticBeanSwapBoostStatisGet(),_str,_args);
    }

    protected static FacadeGame feedDbMoveEffDataDamComp(EffectStatistic _eff) {
        FacadeGame f_ = feedDbMoveEffDataDam(_eff);
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        return f_;
    }
    protected static Struct dispMoveEffStatis(FacadeGame _fac, int _index) {
        return dispMoveEffStatis(_fac, _index,0);
    }
    protected static Struct dispMoveEffStatis(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectStatis(pk_);
        StringMap<String> mapping_ = mappingToEffectStatis();
        return transitEffect(_index,_indexEff,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectStatis(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_STATIS,_pk.beanEffectStatisticBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectStatis() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFSTATIS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_STATIS);
        return map_;
    }
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
//        e_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        e_.getCancelLowStat().add(Statistic.SPEED);
        e_.getCancelChgtStat().add(Statistic.SPEED);
        e_.getCopyBoost().add(Statistic.SPEED);
        e_.getSwapBoostStatis().add(Statistic.SPEED);
        e_.getLocalFailStatis().addEntry(Statistic.SPEED, _failStat);
        e_.getLocalFailSwapBoostStatis().addEntry(Statistic.SPEED, _swapFail);
        return e_;
    }
    protected EffectStatistic withStatisVarRank(EffectStatistic _e, Statistic _statis, int _v) {
        _e.getStatisVarRank().addEntry(_statis,(byte)_v);
        return _e;
    }
    protected EffectStatistic withLawBoost(EffectStatistic _e, Statistic _statis, LgInt _v) {
        _e.getLawBoost().addQuickEvent(_statis,_v);
        return _e;
    }
}
