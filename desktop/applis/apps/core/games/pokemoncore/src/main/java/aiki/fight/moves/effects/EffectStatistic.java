package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.util.IdList;
import code.util.IdMap;


public final class EffectStatistic extends Effect {

    private IdMap<Statistic, Byte> statisVarRank;
    private IdMap<Statistic, String> localFailStatis;
    private Rate evtRate;
    private IdList<Statistic> copyBoost;
    private IdList<Statistic> swapBoostStatis;
    private IdMap<Statistic, String> localFailSwapBoostStatis;
    private MonteCarloEnum<Statistic> lawBoost;
    private IdList<Statistic> cancelLowStat;
    private IdList<Statistic> cancelChgtStat;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkEvents(lawBoost,_data);
        DataInfoChecker.checkPositive(evtRate,_data);
        if (!lawBoost.events().isEmpty() && !Statistic
                .equalsSet(lawBoost.events(), statisVarRank.getKeys())) {
            _data.setError(true);
        }
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),statisVarRank.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),localFailStatis.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),localFailSwapBoostStatis.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(statisVarRank.getKeys(),lawBoost.events(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),copyBoost,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),swapBoostStatis,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),cancelLowStat,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),cancelChgtStat,_data);
        if (!lawBoost.events().isEmpty()||!statisVarRank.isEmpty()) {
            checkWhenLawBoostStatisVarRank(_data);
            return;
        }
        if (!copyBoost.isEmpty()) {
            checkWhenCopyBoost(_data);
            return;
        }
        if (!swapBoostStatis.isEmpty()) {
            checkWhenSwapBoostStatis(_data);
            return;
        }
        if (!cancelLowStat.isEmpty() || !cancelChgtStat.isEmpty()) {
            return;
        }
        _data.setError(true);

    }

    private void checkWhenSwapBoostStatis(DataBase _data) {
        DataInfoChecker.checkEmptyStatisticList(cancelLowStat,_data);
        DataInfoChecker.checkEmptyStatisticList(cancelChgtStat,_data);
        DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
    }

    private void checkWhenCopyBoost(DataBase _data) {
        DataInfoChecker.checkEmptyStatisticList(swapBoostStatis,_data);
        DataInfoChecker.checkEmptyStatisticList(cancelLowStat,_data);
        DataInfoChecker.checkEmptyStatisticList(cancelChgtStat,_data);
        DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
    }

    private void checkWhenLawBoostStatisVarRank(DataBase _data) {
        DataInfoChecker.checkEmptyStatisticList(swapBoostStatis,_data);
        DataInfoChecker.checkEmptyStatisticList(copyBoost,_data);
        DataInfoChecker.checkEmptyStatisticList(cancelLowStat,_data);
        DataInfoChecker.checkEmptyStatisticList(cancelChgtStat,_data);
    }

    public IdMap<Statistic, Byte> getStatisVarRank() {
        return statisVarRank;
    }

    public void setStatisVarRank(IdMap<Statistic, Byte> _statisVarRank) {
        statisVarRank = _statisVarRank;
    }

    public IdMap<Statistic, String> getLocalFailStatis() {
        return localFailStatis;
    }

    public void setLocalFailStatis(IdMap<Statistic, String> _localFailStatis) {
        localFailStatis = _localFailStatis;
    }

    public Rate getEvtRate() {
        return evtRate;
    }

    public void setEvtRate(Rate _evtRate) {
        evtRate = _evtRate;
    }

    public IdList<Statistic> getCopyBoost() {
        return copyBoost;
    }

    public void setCopyBoost(IdList<Statistic> _copyBoost) {
        copyBoost = _copyBoost;
    }

    public IdList<Statistic> getSwapBoostStatis() {
        return swapBoostStatis;
    }

    public void setSwapBoostStatis(IdList<Statistic> _swapBoostStatis) {
        swapBoostStatis = _swapBoostStatis;
    }

    public IdMap<Statistic, String> getLocalFailSwapBoostStatis() {
        return localFailSwapBoostStatis;
    }

    public void setLocalFailSwapBoostStatis(
            IdMap<Statistic, String> _localFailSwapBoostStatis) {
        localFailSwapBoostStatis = _localFailSwapBoostStatis;
    }

    public MonteCarloEnum<Statistic> getLawBoost() {
        return lawBoost;
    }

    public void setLawBoost(MonteCarloEnum<Statistic> _lawBoost) {
        lawBoost = _lawBoost;
    }

    public IdList<Statistic> getCancelLowStat() {
        return cancelLowStat;
    }

    public void setCancelLowStat(IdList<Statistic> _cancelLowStat) {
        cancelLowStat = _cancelLowStat;
    }

    public IdList<Statistic> getCancelChgtStat() {
        return cancelChgtStat;
    }

    public void setCancelChgtStat(IdList<Statistic> _cancelChgtStat) {
        cancelChgtStat = _cancelChgtStat;
    }

}
