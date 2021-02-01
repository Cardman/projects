package aiki.beans.moves.effects;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectStatistic;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatStringTreeMap;
import code.util.StringList;

public class EffectStatisticBean extends EffectBean {
    private NatStringTreeMap< Byte> statisVarRank;

    private NatStringTreeMap< String> localFailStatis;
    private Rate evtRate;
    private String evtRatePerCent;
    private StringList copyBoost;
    private StringList swapBoostStatis;

    private NatStringTreeMap< String> localFailSwapBoostStatis;

    private NatStringTreeMap< Rate> lawBoost;
    private StringList cancelLowStat;
    private StringList cancelChgtStat;
    private int defaultBoost;
    private NatStringTreeMap<String> mapVarsStatistics;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatistic effect_ = (EffectStatistic) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        evtRate = effect_.getEvtRate();
        evtRatePerCent = Rate.multiply(evtRate, new Rate(CST_CENT)).evaluate(2);
        NatStringTreeMap< Byte> statisVarRank_;
        statisVarRank_ = new NatStringTreeMap< Byte>();
        for (Statistic s: effect_.getStatisVarRank().getKeys()) {
            statisVarRank_.put(translatedStatistics_.getVal(s), effect_.getStatisVarRank().getVal(s));
        }
        statisVarRank = statisVarRank_;
        StringList swapBoostStatis_;
        swapBoostStatis_ = new StringList();
        for (Statistic s: effect_.getSwapBoostStatis()) {
            swapBoostStatis_.add(translatedStatistics_.getVal(s));
        }
        swapBoostStatis = swapBoostStatis_;
        NatStringTreeMap< Rate> lawBoost_;
        lawBoost_ = new NatStringTreeMap< Rate>();
        for (Statistic s: effect_.getLawBoost().events()) {
            lawBoost_.put(translatedStatistics_.getVal(s), effect_.getLawBoost().normalizedRate(s));
        }
        lawBoost = lawBoost_;
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatStringTreeMap<String> mapVarsStatistics_;
        mapVarsStatistics_ = new NatStringTreeMap<String>();
        NatStringTreeMap< String> localFailStatis_;
        localFailStatis_ = new NatStringTreeMap<String>();
        for (Statistic s: effect_.getLocalFailStatis().getKeys()) {
            String formula_ = data_.getFormula(effect_.getLocalFailStatis().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailStatis_.put(translatedStatistics_.getVal(s), formula_);
            mapVarsStatistics_.putAllMap(data_.getDescriptions(effect_.getLocalFailStatis().getVal(s), getLanguage()));
        }
        localFailStatis = localFailStatis_;
        NatStringTreeMap< String> localFailSwapBoostStatis_;
        localFailSwapBoostStatis_ = new NatStringTreeMap<String>();
        for (Statistic s: effect_.getLocalFailSwapBoostStatis().getKeys()) {
            String formula_ = data_.getFormula(effect_.getLocalFailSwapBoostStatis().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailSwapBoostStatis_.put(translatedStatistics_.getVal(s), formula_);
            mapVarsStatistics_.putAllMap(data_.getDescriptions(effect_.getLocalFailSwapBoostStatis().getVal(s), getLanguage()));
        }
        localFailSwapBoostStatis = localFailSwapBoostStatis_;
        mapVarsStatistics = mapVarsStatistics_;
        defaultBoost = data_.getDefaultBoost();
        StringList cancelLowStat_;
        cancelLowStat_ = new StringList();
        for (Statistic s: effect_.getCancelLowStat()) {
            cancelLowStat_.add(translatedStatistics_.getVal(s));
        }
        cancelLowStat = cancelLowStat_;
        StringList cancelChgtStat_;
        cancelChgtStat_ = new StringList();
        for (Statistic s: effect_.getCancelChgtStat()) {
            cancelChgtStat_.add(translatedStatistics_.getVal(s));
        }
        cancelChgtStat = cancelChgtStat_;
        StringList copyBoost_;
        copyBoost_ = new StringList();
        for (Statistic s: effect_.getCopyBoost()) {
            copyBoost_.add(translatedStatistics_.getVal(s));
        }
        copyBoost = copyBoost_;
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
        if (!localFailStatis.contains(stat_)) {
            return DataBase.EMPTY_STRING;
        }
        return localFailStatis.getVal(stat_);
    }
    public String getSwapFail(int _index) {
        String stat_ = swapBoostStatis.get(_index);
        if (!localFailSwapBoostStatis.contains(stat_)) {
            return DataBase.EMPTY_STRING;
        }
        return localFailSwapBoostStatis.getVal(stat_);
    }
    public boolean isAlwaysEnabled() {
        return Rate.eq(evtRate, Rate.one());
    }

    public Rate getEvtRate() {
        return evtRate;
    }

    public String getEvtRatePerCent() {
        return evtRatePerCent;
    }

    public NatStringTreeMap<Byte> getStatisVarRank() {
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

    public int getDefaultBoost() {
        return defaultBoost;
    }

    public StringList getCancelChgtStat() {
        return cancelChgtStat;
    }

    public StringList getCopyBoost() {
        return copyBoost;
    }
}