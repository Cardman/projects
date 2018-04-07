package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.annot.RwXml;

@RwXml
public final class EffectStatistic extends Effect {

    private EnumMap<Statistic,Byte> statisVarRank;
    private EnumMap<Statistic,String> localFailStatis;
    private Rate evtRate;
    private EnumList<Statistic> copyBoost;
    private EnumList<Statistic> swapBoostStatis;
    private EnumMap<Statistic,String> localFailSwapBoostStatis;
    private MonteCarloEnum<Statistic> lawBoost;
    private EnumList<Statistic> cancelLowStat;
    private EnumList<Statistic> cancelChgtStat;
    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!lawBoost.checkEvents()) {
            throw new DataException();
        }
        if (!evtRate.isZeroOrGt()) {
            throw new DataException();
        }
        for (EntryCust<Statistic,Byte> e: statisVarRank.entryList()) {
            if (!Statistic.getStatisticsWithBoost().containsObj(e.getKey())) {
                throw new DataException();
            }
            e.getValue().byteValue();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(lawBoost.events())) {
            throw new DataException();
        }
        if (!lawBoost.events().isEmpty()) {
            if (!Statistic.equalsSet(lawBoost.events(), statisVarRank.getKeys())) {
                throw new DataException();
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(localFailStatis.getKeys())) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(localFailSwapBoostStatis.getKeys())) {
            throw new DataException();
        }
        if (!statisVarRank.containsAllAsKeys(lawBoost.events())) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(copyBoost)) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(swapBoostStatis)) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelLowStat)) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelChgtStat)) {
            throw new DataException();
        }
        if (!lawBoost.events().isEmpty()) {
            if (evtRate.isZero()) {
                throw new DataException();
            }
            if (!swapBoostStatis.isEmpty()) {
                throw new DataException();
            }
            if (!copyBoost.isEmpty()) {
                throw new DataException();
            }
            if (!cancelLowStat.isEmpty()) {
                throw new DataException();
            }
            if (!cancelChgtStat.isEmpty()) {
                throw new DataException();
            }
            return;
        }
        if (evtRate.isZero()) {
            throw new DataException();
        }
        if (!statisVarRank.isEmpty()) {
            if (!swapBoostStatis.isEmpty()) {
                throw new DataException();
            }
            if (!copyBoost.isEmpty()) {
                throw new DataException();
            }
            if (!cancelLowStat.isEmpty()) {
                throw new DataException();
            }
            if (!cancelChgtStat.isEmpty()) {
                throw new DataException();
            }
            return;
        }
        if (!copyBoost.isEmpty()) {
            if (!swapBoostStatis.isEmpty()) {
                throw new DataException();
            }
            if (!cancelLowStat.isEmpty()) {
                throw new DataException();
            }
            if (!cancelChgtStat.isEmpty()) {
                throw new DataException();
            }
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                throw new DataException();
            }
            return;
        }
        if (!swapBoostStatis.isEmpty()) {
            if (!cancelLowStat.isEmpty()) {
                throw new DataException();
            }
            if (!cancelChgtStat.isEmpty()) {
                throw new DataException();
            }
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                throw new DataException();
            }
            return;
        }
        if (!cancelLowStat.isEmpty() || !cancelChgtStat.isEmpty()) {
            return;
        }
        throw new DataException();
    }

    public EnumMap<Statistic,Byte> getStatisVarRank() {
        return statisVarRank;
    }
    public void setStatisVarRank(EnumMap<Statistic,Byte> _statisVarRank) {
        statisVarRank = _statisVarRank;
    }
    public EnumMap<Statistic,String> getLocalFailStatis() {
        return localFailStatis;
    }
    public void setLocalFailStatis(EnumMap<Statistic,String> _localFailStatis) {
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
    public EnumMap<Statistic,String> getLocalFailSwapBoostStatis() {
        return localFailSwapBoostStatis;
    }
    public void setLocalFailSwapBoostStatis(EnumMap<Statistic,String> _localFailSwapBoostStatis) {
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
