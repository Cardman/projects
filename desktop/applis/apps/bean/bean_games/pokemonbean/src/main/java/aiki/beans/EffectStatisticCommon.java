package aiki.beans;

import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.instances.Instances;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.util.*;
import code.util.core.StringUtil;

public final class EffectStatisticCommon {
    private DictionaryComparator<TranslatedKey,StatRankRate> statisVarRank;

//    private NatStringTreeMap< String> localFailStatis;
    private Rate evtRate;
//    private String evtRatePerCent;
    private CustList<TranslatedKey> copyBoost;
//    private StringList swapBoostStatis;
    private DictionaryComparator<TranslatedKey,String> swapBoostStatis;

//    private NatStringTreeMap< String> localFailSwapBoostStatis;

    private MonteCarloEnum<Statistic> lawBoost;
    private CustList<TranslatedKey> cancelLowStat;
    private CustList<TranslatedKey> cancelChgtStat;
    private long defaultBoost;
    private NatStringTreeMap<String> mapVarsStatistics;

    public void init(FacadeGame _data, String _lg, EffectStatistic _statis, boolean _withEff) {
        EffectStatistic adj_ = eff(_data.getData(), _statis, _withEff);
//        AbsMap<Statistic,String> translatedStatistics_ = _data.getData().getTranslatedStatistics().getVal(_lg);
        evtRate = adj_.getEvtRate();
//        evtRatePerCent = Rate.multiply(evtRate, new Rate(CommonBean.CST_CENT)).evaluate(2);
//        NatStringTreeMap< Rate> lawBoost_;
//        lawBoost_ = new NatStringTreeMap< Rate>();
//        for (Statistic s: adj_.getLawBoost().eventsDiff()) {
//            lawBoost_.put(translatedStatistics_.getVal(s), adj_.getLawBoost().normalizedRate(s));
//        }
        lawBoost = adj_.getLawBoost();
        DictionaryComparator<TranslatedKey,StatRankRate> statisVarRank_;
        statisVarRank_ = new DictionaryComparator<TranslatedKey,StatRankRate>(new ComparingTranslatedKey());
        for (Statistic s: adj_.getStatisVarRank().getKeys()) {
            String formula_ = _data.getData().getFormula(StringUtil.nullToEmpty(adj_.getLocalFailStatis().getVal(s)), _lg);
            if (adj_.getLawBoost().isZero()) {
                statisVarRank_.put(CommonBean.buildSi(_data,s), new StatRankRate(adj_.getStatisVarRank(),s,formula_,Rate.one()));
            } else {
                statisVarRank_.put(CommonBean.buildSi(_data,s), new StatRankRate(adj_.getStatisVarRank(),s,formula_,adj_.getLawBoost().normalizedRate(s)));
            }
        }
        for (Statistic s: adj_.getLawBoost().eventsDiff()) {
            String formula_ = _data.getData().getFormula(StringUtil.nullToEmpty(adj_.getLocalFailStatis().getVal(s)), _lg);
            statisVarRank_.put(CommonBean.buildSi(_data,s), new StatRankRate(adj_.getStatisVarRank(),s,formula_,adj_.getLawBoost().normalizedRate(s)));
        }
        statisVarRank = statisVarRank_;
        DictionaryComparator<TranslatedKey,String> swapBoostStatis_;
        swapBoostStatis_ = new DictionaryComparator<TranslatedKey,String>(new ComparingTranslatedKey());
        for (Statistic s: adj_.getSwapBoostStatis()) {
            String formula_ = _data.getData().getFormula(StringUtil.nullToEmpty(adj_.getLocalFailSwapBoostStatis().getVal(s)), _lg);
            swapBoostStatis_.addEntry(CommonBean.buildSi(_data,s),formula_);
        }
//        swapBoostStatis_.sort();
        swapBoostStatis = swapBoostStatis_;
//            Map<String,String> loc_ = new Map<>();
//            loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatStringTreeMap<String> mapVarsStatistics_;
        mapVarsStatistics_ = new NatStringTreeMap<String>();
//        NatStringTreeMap< String> localFailStatis_;
//        localFailStatis_ = new NatStringTreeMap<String>();
        for (Statistic s: adj_.getLocalFailStatis().getKeys()) {
//            String formula_ = _data.getData().getFormula(adj_.getLocalFailStatis().getVal(s), _lg);
//                formula_ = StringList.replace(formula_, loc_);
//                formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//                formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            localFailStatis_.put(translatedStatistics_.getVal(s), formula_);
            mapVarsStatistics_.putAllMap(_data.getData().getDescriptions(adj_.getLocalFailStatis().getVal(s), _lg));
        }
//        localFailStatis = localFailStatis_;
//        NatStringTreeMap< String> localFailSwapBoostStatis_;
//        localFailSwapBoostStatis_ = new NatStringTreeMap<String>();
        for (Statistic s: adj_.getLocalFailSwapBoostStatis().getKeys()) {
//            String formula_ = _data.getData().getFormula(adj_.getLocalFailSwapBoostStatis().getVal(s), _lg);
//                formula_ = StringList.replace(formula_, loc_);
//                formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//                formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            localFailSwapBoostStatis_.put(translatedStatistics_.getVal(s), formula_);
            mapVarsStatistics_.putAllMap(_data.getData().getDescriptions(adj_.getLocalFailSwapBoostStatis().getVal(s), _lg));
        }
//        localFailSwapBoostStatis = localFailSwapBoostStatis_;
        mapVarsStatistics = mapVarsStatistics_;
//        StringList cancelLowStat_;
//        cancelLowStat_ = new StringList();
//        for (Statistic s: adj_.getCancelLowStat()) {
//            cancelLowStat_.add(translatedStatistics_.getVal(s));
//        }
//        cancelLowStat_.sort();
        cancelLowStat = CommonBean.listTrStringsSi(adj_.getCancelLowStat(),_data);
//        StringList cancelChgtStat_;
//        cancelChgtStat_ = new StringList();
//        for (Statistic s: adj_.getCancelChgtStat()) {
//            cancelChgtStat_.add(translatedStatistics_.getVal(s));
//        }
//        cancelChgtStat_.sort();
        cancelChgtStat = CommonBean.listTrStringsSi(adj_.getCancelChgtStat(),_data);
//        StringList copyBoost_;
//        copyBoost_ = new StringList();
//        for (Statistic s: adj_.getCopyBoost()) {
//            copyBoost_.add(translatedStatistics_.getVal(s));
//        }
//        copyBoost_.sort();
        copyBoost = CommonBean.listTrStringsSi(adj_.getCopyBoost(),_data);
    }
    private EffectStatistic eff(DataBase _data, EffectStatistic _statis, boolean _withEff) {
        if (!_withEff) {
            defaultBoost = 0;
            return Instances.newEffectStatistic();
        }
        defaultBoost = _data.getDefaultBoost();
        return _statis;
    }
    public boolean notEmptyVarBoost() {
        return !statisVarRank.isEmpty();
    }
    public boolean randomStatis() {
        return !lawBoost.isEmpty();
    }
    public Rate getRate(int _index) {
        return statisVarRank.getValue(_index).getRate();
    }
    public String getFail(int _index) {
        return statisVarRank.getValue(_index).getFail();
//        String stat_ = statisVarRank.getKey(_index).getTranslation();
//        return StringUtil.nullToEmpty(localFailStatis.getVal(stat_));
    }
    public String getSwapFail(int _index) {
        return swapBoostStatis.getValue(_index);
//        String stat_ = swapBoostStatis.get(_index);
//        return StringUtil.nullToEmpty(localFailSwapBoostStatis.getVal(stat_));
    }
    public boolean isAlwaysEnabled() {
        return Rate.eq(evtRate, Rate.one());
    }

    public Rate getEvtRate() {
        return evtRate;
    }

    public String getEvtRatePerCent() {
        return Rate.multiply(evtRate, new Rate(CommonBean.CST_CENT)).evaluate(2);
    }

    public DictionaryComparator<TranslatedKey,StatRankRate> getStatisVarRank() {
        return statisVarRank;
    }

    public NatStringTreeMap<String> getMapVarsStatistics() {
        return mapVarsStatistics;
    }

    public DictionaryComparator<TranslatedKey, String> getSwapBoostStatis() {
        return swapBoostStatis;
    }

    public CustList<TranslatedKey> getCancelLowStat() {
        return cancelLowStat;
    }

    public long getDefaultBoost() {
        return defaultBoost;
    }

    public CustList<TranslatedKey> getCancelChgtStat() {
        return cancelChgtStat;
    }

    public CustList<TranslatedKey> getCopyBoost() {
        return copyBoost;
    }
}
