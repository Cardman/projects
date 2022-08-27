package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.AbsMap;


public final class EffectStatistic extends Effect {

    private AbsMap<Statistic, Byte> statisVarRank;
    private AbsMap<Statistic, String> localFailStatis;
    private Rate evtRate;
    private EnumList<Statistic> copyBoost;
    private EnumList<Statistic> swapBoostStatis;
    private AbsMap<Statistic, String> localFailSwapBoostStatis;
    private MonteCarloEnum<Statistic> lawBoost;
    private EnumList<Statistic> cancelLowStat;
    private EnumList<Statistic> cancelChgtStat;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!lawBoost.checkEvents()) {
            _data.setError(true);
        }
        if (!evtRate.isZeroOrGt()) {
            _data.setError(true);
        }
        for (EntryCust<Statistic, Byte> e : statisVarRank.entryList()) {
            if (!Statistic.getStatisticsWithBoost().containsObj(e.getKey())) {
                _data.setError(true);
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                lawBoost.events())) {
            _data.setError(true);
        }
        if (!lawBoost.events().isEmpty()) {
            if (!Statistic
                    .equalsSet(lawBoost.events(), statisVarRank.getKeys())) {
                _data.setError(true);
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                localFailStatis.getKeys())) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                localFailSwapBoostStatis.getKeys())) {
            _data.setError(true);
        }
        if (!statisVarRank.containsAllAsKeys(lawBoost.events())) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(copyBoost)) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(swapBoostStatis)) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelLowStat)) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelChgtStat)) {
            _data.setError(true);
        }
        if (evtRate.isZero()) {
            _data.setError(true);
        }
        if (!lawBoost.events().isEmpty()||!statisVarRank.isEmpty()) {
            if (!swapBoostStatis.isEmpty()) {
                _data.setError(true);
            }
            if (!copyBoost.isEmpty()) {
                _data.setError(true);
            }
            if (!cancelLowStat.isEmpty()) {
                _data.setError(true);
            }
            if (!cancelChgtStat.isEmpty()) {
                _data.setError(true);
            }
            return;
        }
        if (!copyBoost.isEmpty()) {
            if (!swapBoostStatis.isEmpty()) {
                _data.setError(true);
            }
            if (!cancelLowStat.isEmpty()) {
                _data.setError(true);
            }
            if (!cancelChgtStat.isEmpty()) {
                _data.setError(true);
            }
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                _data.setError(true);
            }
            return;
        }
        if (!swapBoostStatis.isEmpty()) {
            if (!cancelLowStat.isEmpty()) {
                _data.setError(true);
            }
            if (!cancelChgtStat.isEmpty()) {
                _data.setError(true);
            }
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                _data.setError(true);
            }
            return;
        }
        if (!cancelLowStat.isEmpty() || !cancelChgtStat.isEmpty()) {
            return;
        }
        _data.setError(true);

    }

    public AbsMap<Statistic, Byte> getStatisVarRank() {
        return statisVarRank;
    }

    public void setStatisVarRank(AbsMap<Statistic, Byte> _statisVarRank) {
        statisVarRank = _statisVarRank;
    }

    public AbsMap<Statistic, String> getLocalFailStatis() {
        return localFailStatis;
    }

    public void setLocalFailStatis(AbsMap<Statistic, String> _localFailStatis) {
        localFailStatis = _localFailStatis;
    }

    public Rate getEvtRate() {
        return evtRate;
    }

    public void setEvtRate(Rate _evtRate) {
        evtRate = _evtRate;
    }

    public EnumList<Statistic> getCopyBoost() {
        return copyBoost;
    }

    public void setCopyBoost(EnumList<Statistic> _copyBoost) {
        copyBoost = _copyBoost;
    }

    public EnumList<Statistic> getSwapBoostStatis() {
        return swapBoostStatis;
    }

    public void setSwapBoostStatis(EnumList<Statistic> _swapBoostStatis) {
        swapBoostStatis = _swapBoostStatis;
    }

    public AbsMap<Statistic, String> getLocalFailSwapBoostStatis() {
        return localFailSwapBoostStatis;
    }

    public void setLocalFailSwapBoostStatis(
            AbsMap<Statistic, String> _localFailSwapBoostStatis) {
        localFailSwapBoostStatis = _localFailSwapBoostStatis;
    }

    public MonteCarloEnum<Statistic> getLawBoost() {
        return lawBoost;
    }

    public void setLawBoost(MonteCarloEnum<Statistic> _lawBoost) {
        lawBoost = _lawBoost;
    }

    public EnumList<Statistic> getCancelLowStat() {
        return cancelLowStat;
    }

    public void setCancelLowStat(EnumList<Statistic> _cancelLowStat) {
        cancelLowStat = _cancelLowStat;
    }

    public EnumList<Statistic> getCancelChgtStat() {
        return cancelChgtStat;
    }

    public void setCancelChgtStat(EnumList<Statistic> _cancelChgtStat) {
        cancelChgtStat = _cancelChgtStat;
    }

}
