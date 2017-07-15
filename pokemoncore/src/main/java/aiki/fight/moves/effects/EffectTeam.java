package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.CategoryMult;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public class EffectTeam extends Effect {

    @CheckedData
    private boolean forbiddingHealing;
    private EnumList<Statistic> forbiddenBoost;
    private StringList unusableMoves;
    private EnumList<Statistic> cancelChgtStatFoeTeam;
    private EnumList<Statistic> cancelChgtStatTeam;

    private ObjectMap<CategoryMult,Rate> multDamage;

    private EnumMap<Statistic,Rate> multStatistic;
    private EnumMap<Statistic,Rate> multStatisticFoe;
    private EnumList<Statistic> protectAgainstLowStat;
    @CheckedData
    private boolean protectAgainstCh;
    private StringList protectAgainstStatus;
    private StringList disableFoeTeamEffects;
    private StringList disableFoeTeamStatus;
    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(unusableMoves)) {
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(protectAgainstStatus)) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(disableFoeTeamEffects)) {
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(disableFoeTeamStatus)) {
            throw new DataException();
        }
        for (CategoryMult k: multDamage.getKeys()) {
            if (!_data.getCategories().containsObj(k.getCategory())) {
                throw new DataException();
            }
            if (k.getMult() > DataBase.MAX_MULT_FIGHT) {
                throw new DataException();
            }
            if (k.getMult() < 1) {
                throw new DataException();
            }
            if (!multDamage.getVal(k).isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (Statistic k: multStatisticFoe.getKeys()) {
            if (!k.isBoost()) {
                throw new DataException();
            }
            if (!multStatisticFoe.getVal(k).isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (Statistic k: multStatistic.getKeys()) {
            if (!k.isBoost()) {
                throw new DataException();
            }
            if (!multStatistic.getVal(k).isZeroOrGt()) {
                throw new DataException();
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(protectAgainstLowStat)) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(forbiddenBoost)) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelChgtStatFoeTeam)) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelChgtStatTeam)) {
            throw new DataException();
        }
    }

    public boolean getForbiddingHealing() {
        return forbiddingHealing;
    }
    public void setForbiddingHealing(boolean _forbiddingHealing) {
        forbiddingHealing = _forbiddingHealing;
    }
    public EnumList<Statistic> getForbiddenBoost() {
        return forbiddenBoost;
    }
    public void setForbiddenBoost(EnumList<Statistic> _forbiddenBoost) {
        forbiddenBoost = _forbiddenBoost;
    }
    public StringList getUnusableMoves() {
        return unusableMoves;
    }
    public void setUnusableMoves(StringList _unusableMoves) {
        unusableMoves = _unusableMoves;
    }
    public EnumList<Statistic> getCancelChgtStatFoeTeam() {
        return cancelChgtStatFoeTeam;
    }
    public void setCancelChgtStatFoeTeam(EnumList<Statistic> _cancelChgtStatFoeTeam) {
        cancelChgtStatFoeTeam = _cancelChgtStatFoeTeam;
    }
    public EnumList<Statistic> getCancelChgtStatTeam() {
        return cancelChgtStatTeam;
    }
    public void setCancelChgtStatTeam(EnumList<Statistic> _cancelChgtStatTeam) {
        cancelChgtStatTeam = _cancelChgtStatTeam;
    }

    public ObjectMap<CategoryMult,Rate> getMultDamage() {
        return multDamage;
    }

    public void setMultDamage(ObjectMap<CategoryMult,Rate> _multDamage) {
        multDamage = _multDamage;
    }

    public EnumMap<Statistic,Rate> getMultStatistic() {
        return multStatistic;
    }
    public void setMultStatistic(EnumMap<Statistic,Rate> _multStatistic) {
        multStatistic = _multStatistic;
    }
    public EnumMap<Statistic,Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }
    public void setMultStatisticFoe(EnumMap<Statistic,Rate> _multStatisticFoe) {
        multStatisticFoe = _multStatisticFoe;
    }
    public EnumList<Statistic> getProtectAgainstLowStat() {
        return protectAgainstLowStat;
    }
    public void setProtectAgainstLowStat(EnumList<Statistic> _protectAgainstLowStat) {
        protectAgainstLowStat = _protectAgainstLowStat;
    }
    public boolean getProtectAgainstCh() {
        return protectAgainstCh;
    }
    public void setProtectAgainstCh(boolean _protectAgainstCh) {
        protectAgainstCh = _protectAgainstCh;
    }
    public StringList getProtectAgainstStatus() {
        return protectAgainstStatus;
    }
    public void setProtectAgainstStatus(StringList _protectAgainstStatus) {
        protectAgainstStatus = _protectAgainstStatus;
    }
    public StringList getDisableFoeTeamEffects() {
        return disableFoeTeamEffects;
    }
    public void setDisableFoeTeamEffects(StringList _disableFoeTeamEffects) {
        disableFoeTeamEffects = _disableFoeTeamEffects;
    }

    public StringList getDisableFoeTeamStatus() {
        return disableFoeTeamStatus;
    }

    public void setDisableFoeTeamStatus(StringList _disableFoeTeamStatus) {
        disableFoeTeamStatus = _disableFoeTeamStatus;
    }
}
