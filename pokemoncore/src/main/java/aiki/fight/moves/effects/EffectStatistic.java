package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;


public final class EffectStatistic extends Effect {

    private EnumMap<Statistic, Byte> statisVarRank;
    private EnumMap<Statistic, String> localFailStatis;
    private Rate evtRate;
    private EnumList<Statistic> copyBoost;
    private EnumList<Statistic> swapBoostStatis;
    private EnumMap<Statistic, String> localFailSwapBoostStatis;
    private MonteCarloEnum<Statistic> lawBoost;
    private EnumList<Statistic> cancelLowStat;
    private EnumList<Statistic> cancelChgtStat;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!lawBoost.checkEvents()) {
            _data.setError(true);
            return;

        }
        if (!evtRate.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        for (EntryCust<Statistic, Byte> e : statisVarRank.entryList()) {
            if (!Statistic.getStatisticsWithBoost().containsObj(e.getKey())) {
                _data.setError(true);
                return;

            }
            e.getValue().byteValue();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                lawBoost.events())) {
            _data.setError(true);
            return;

        }
        if (!lawBoost.events().isEmpty()) {
            if (!Statistic
                    .equalsSet(lawBoost.events(), statisVarRank.getKeys())) {
                _data.setError(true);
                return;

            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                localFailStatis.getKeys())) {
            _data.setError(true);
            return;

        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                localFailSwapBoostStatis.getKeys())) {
            _data.setError(true);
            return;

        }
        if (!statisVarRank.containsAllAsKeys(lawBoost.events())) {
            _data.setError(true);
            return;

        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(copyBoost)) {
            _data.setError(true);
            return;

        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(swapBoostStatis)) {
            _data.setError(true);
            return;

        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelLowStat)) {
            _data.setError(true);
            return;

        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelChgtStat)) {
            _data.setError(true);
            return;

        }
        if (!lawBoost.events().isEmpty()) {
            if (evtRate.isZero()) {
                _data.setError(true);
                return;

            }
            if (!swapBoostStatis.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!copyBoost.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!cancelLowStat.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!cancelChgtStat.isEmpty()) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (evtRate.isZero()) {
            _data.setError(true);
            return;

        }
        if (!statisVarRank.isEmpty()) {
            if (!swapBoostStatis.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!copyBoost.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!cancelLowStat.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!cancelChgtStat.isEmpty()) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (!copyBoost.isEmpty()) {
            if (!swapBoostStatis.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!cancelLowStat.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!cancelChgtStat.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (!swapBoostStatis.isEmpty()) {
            if (!cancelLowStat.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!cancelChgtStat.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (!cancelLowStat.isEmpty() || !cancelChgtStat.isEmpty()) {
            return;
        }
        _data.setError(true);
        return;

    }

    public EnumMap<Statistic, Byte> getStatisVarRank() {
        return statisVarRank;
    }

    public void setStatisVarRank(EnumMap<Statistic, Byte> _statisVarRank) {
        statisVarRank = _statisVarRank;
    }

    public EnumMap<Statistic, String> getLocalFailStatis() {
        return localFailStatis;
    }

    public void setLocalFailStatis(EnumMap<Statistic, String> _localFailStatis) {
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

    public EnumMap<Statistic, String> getLocalFailSwapBoostStatis() {
        return localFailSwapBoostStatis;
    }

    public void setLocalFailSwapBoostStatis(
            EnumMap<Statistic, String> _localFailSwapBoostStatis) {
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
