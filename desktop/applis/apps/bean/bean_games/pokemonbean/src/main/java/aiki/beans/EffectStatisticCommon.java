package aiki.beans;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.instances.Instances;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class EffectStatisticCommon {
    private NatStringTreeMap< Long> statisVarRank;

    private NatStringTreeMap< String> localFailStatis;
    private Rate evtRate;
//    private String evtRatePerCent;
    private StringList copyBoost;
    private StringList swapBoostStatis;

    private NatStringTreeMap< String> localFailSwapBoostStatis;

    private NatStringTreeMap< Rate> lawBoost;
    private StringList cancelLowStat;
    private StringList cancelChgtStat;
    private long defaultBoost;
    private NatStringTreeMap<String> mapVarsStatistics;

    public void init(DataBase _data, String _lg, EffectStatistic _statis, boolean _withEff) {
        EffectStatistic adj_ = eff(_data, _statis, _withEff);
        AbsMap<Statistic,String> translatedStatistics_ = _data.getTranslatedStatistics().getVal(_lg);
        evtRate = adj_.getEvtRate();
//        evtRatePerCent = Rate.multiply(evtRate, new Rate(CommonBean.CST_CENT)).evaluate(2);
        NatStringTreeMap< Long> statisVarRank_;
        statisVarRank_ = new NatStringTreeMap< Long>();
        for (Statistic s: adj_.getStatisVarRank().getKeys()) {
            statisVarRank_.put(translatedStatistics_.getVal(s), adj_.getStatisVarRank().getVal(s));
        }
        statisVarRank = statisVarRank_;
        StringList swapBoostStatis_;
        swapBoostStatis_ = new StringList();
        for (Statistic s: adj_.getSwapBoostStatis()) {
            swapBoostStatis_.add(translatedStatistics_.getVal(s));
        }
        swapBoostStatis_.sort();
        swapBoostStatis = swapBoostStatis_;
        NatStringTreeMap< Rate> lawBoost_;
        lawBoost_ = new NatStringTreeMap< Rate>();
        for (Statistic s: adj_.getLawBoost().eventsDiff()) {
            lawBoost_.put(translatedStatistics_.getVal(s), adj_.getLawBoost().normalizedRate(s));
        }
        lawBoost = lawBoost_;
//            Map<String,String> loc_ = new Map<>();
//            loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatStringTreeMap<String> mapVarsStatistics_;
        mapVarsStatistics_ = new NatStringTreeMap<String>();
        NatStringTreeMap< String> localFailStatis_;
        localFailStatis_ = new NatStringTreeMap<String>();
        for (Statistic s: adj_.getLocalFailStatis().getKeys()) {
            String formula_ = _data.getFormula(adj_.getLocalFailStatis().getVal(s), _lg);
//                formula_ = StringList.replace(formula_, loc_);
//                formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//                formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailStatis_.put(translatedStatistics_.getVal(s), formula_);
            mapVarsStatistics_.putAllMap(_data.getDescriptions(adj_.getLocalFailStatis().getVal(s), _lg));
        }
        localFailStatis = localFailStatis_;
        NatStringTreeMap< String> localFailSwapBoostStatis_;
        localFailSwapBoostStatis_ = new NatStringTreeMap<String>();
        for (Statistic s: adj_.getLocalFailSwapBoostStatis().getKeys()) {
            String formula_ = _data.getFormula(adj_.getLocalFailSwapBoostStatis().getVal(s), _lg);
//                formula_ = StringList.replace(formula_, loc_);
//                formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//                formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailSwapBoostStatis_.put(translatedStatistics_.getVal(s), formula_);
            mapVarsStatistics_.putAllMap(_data.getDescriptions(adj_.getLocalFailSwapBoostStatis().getVal(s), _lg));
        }
        localFailSwapBoostStatis = localFailSwapBoostStatis_;
        mapVarsStatistics = mapVarsStatistics_;
        StringList cancelLowStat_;
        cancelLowStat_ = new StringList();
        for (Statistic s: adj_.getCancelLowStat()) {
            cancelLowStat_.add(translatedStatistics_.getVal(s));
        }
        cancelLowStat_.sort();
        cancelLowStat = cancelLowStat_;
        StringList cancelChgtStat_;
        cancelChgtStat_ = new StringList();
        for (Statistic s: adj_.getCancelChgtStat()) {
            cancelChgtStat_.add(translatedStatistics_.getVal(s));
        }
        cancelChgtStat_.sort();
        cancelChgtStat = cancelChgtStat_;
        StringList copyBoost_;
        copyBoost_ = new StringList();
        for (Statistic s: adj_.getCopyBoost()) {
            copyBoost_.add(translatedStatistics_.getVal(s));
        }
        copyBoost_.sort();
        copyBoost = copyBoost_;
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
        return lawBoost.getValue(_index);
    }
    public String getFail(int _index) {
        String stat_ = statisVarRank.getKey(_index);
        return StringUtil.nullToEmpty(localFailStatis.getVal(stat_));
    }
    public String getSwapFail(int _index) {
        String stat_ = swapBoostStatis.get(_index);
        return StringUtil.nullToEmpty(localFailSwapBoostStatis.getVal(stat_));
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

    public NatStringTreeMap<Long> getStatisVarRank() {
        return statisVarRank;
    }

    public NatStringTreeMap<String> getMapVarsStatistics() {
        return mapVarsStatistics;
    }

    public StringList getSwapBoostStatis() {
        return swapBoostStatis;
    }

    public StringList getCancelLowStat() {
        return cancelLowStat;
    }

    public long getDefaultBoost() {
        return defaultBoost;
    }

    public StringList getCancelChgtStat() {
        return cancelChgtStat;
    }

    public StringList getCopyBoost() {
        return copyBoost;
    }
}
