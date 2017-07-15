package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.StringList;
import aiki.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectStatistic;

public class EffectStatisticBean extends EffectBean {

    @Accessible
    private NatTreeMap<String, Byte> statisVarRank;

    private NatTreeMap<String, String> localFailStatis;

    @Accessible
    private Rate evtRate;

    @Accessible
    private String evtRatePerCent;

    @Accessible
    private StringList copyBoost;

    @Accessible
    private StringList swapBoostStatis;

    private NatTreeMap<String, String> localFailSwapBoostStatis;

    private NatTreeMap<String, Rate> lawBoost;

    @Accessible
    private StringList cancelLowStat;

    @Accessible
    private StringList cancelChgtStat;

    @Accessible
    private int defaultBoost;

    @Accessible
    private NatTreeMap<String,String> mapVarsStatistics;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatistic effect_ = (EffectStatistic) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        evtRate = effect_.getEvtRate();
        evtRatePerCent = Rate.multiply(evtRate, new Rate(CENT)).evaluate(2);
        NatTreeMap<String, Byte> statisVarRank_;
        statisVarRank_ = new NatTreeMap<String, Byte>();
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
        NatTreeMap<String, Rate> lawBoost_;
        lawBoost_ = new NatTreeMap<String, Rate>();
        LgInt sum_ = effect_.getLawBoost().sum();
        for (Statistic s: effect_.getLawBoost().events()) {
            lawBoost_.put(translatedStatistics_.getVal(s), new Rate(effect_.getLawBoost().rate(s), sum_));
        }
        lawBoost = lawBoost_;
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatTreeMap<String,String> mapVarsStatistics_;
        mapVarsStatistics_ = new NatTreeMap<String,String>();
        NatTreeMap<String, String> localFailStatis_;
        localFailStatis_ = new NatTreeMap<String,String>();
        for (Statistic s: effect_.getLocalFailStatis().getKeys()) {
            String formula_ = data_.getFormula(effect_.getLocalFailStatis().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailStatis_.put(translatedStatistics_.getVal(s), formula_);
            mapVarsStatistics_.putAllTreeMap(data_.getDescriptions(effect_.getLocalFailStatis().getVal(s), getLanguage()));
        }
        localFailStatis = localFailStatis_;
        NatTreeMap<String, String> localFailSwapBoostStatis_;
        localFailSwapBoostStatis_ = new NatTreeMap<String,String>();
        for (Statistic s: effect_.getLocalFailSwapBoostStatis().getKeys()) {
            String formula_ = data_.getFormula(effect_.getLocalFailSwapBoostStatis().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailSwapBoostStatis_.put(translatedStatistics_.getVal(s), formula_);
            mapVarsStatistics_.putAllTreeMap(data_.getDescriptions(effect_.getLocalFailSwapBoostStatis().getVal(s), getLanguage()));
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

    @Accessible
    private boolean notEmptyVarBoost() {
        return !statisVarRank.isEmpty();
    }

    @Accessible
    private boolean randomStatis() {
        return !lawBoost.isEmpty();
    }

    @Accessible
    private Rate getRate(Long _index) {
        return lawBoost.getValue(_index.intValue());
    }

    @Accessible
    private String getFail(Long _index) {
        String stat_ = statisVarRank.getKey(_index.intValue());
        if (!localFailStatis.contains(stat_)) {
            return DataBase.EMPTY_STRING;
        }
        return localFailStatis.getVal(stat_);
    }

    @Accessible
    private String getSwapFail(Long _index) {
        String stat_ = swapBoostStatis.get(_index.intValue());
        if (!localFailSwapBoostStatis.contains(stat_)) {
            return DataBase.EMPTY_STRING;
        }
        return localFailSwapBoostStatis.getVal(stat_);
    }

    @Accessible
    private boolean isAlwaysEnabled() {
        return Rate.eq(evtRate, Rate.one());
    }
}
