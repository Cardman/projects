package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.CategoryMult;
import aiki.fight.util.CategoryMults;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.EnumList;
import code.util.StringList;


public final class EffectTeam extends Effect {

    private boolean forbiddingHealing;
    private EnumList<Statistic> forbiddenBoost;
    private StringList unusableMoves;
    private EnumList<Statistic> cancelChgtStatFoeTeam;
    private EnumList<Statistic> cancelChgtStatTeam;

    private CategoryMults multDamage;

    private AbsMap<Statistic, Rate> multStatistic;
    private AbsMap<Statistic, Rate> multStatisticFoe;
    private EnumList<Statistic> protectAgainstLowStat;
    private boolean protectAgainstCh;
    private StringList protectAgainstStatus;
    private StringList disableFoeTeamEffects;
    private StringList disableFoeTeamStatus;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),unusableMoves,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),protectAgainstStatus,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),disableFoeTeamEffects,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),disableFoeTeamStatus,_data);
        for (CategoryMult k : multDamage.getKeys()) {
            DataInfoChecker.checkStringListContains(_data.getCategories(),k.getCategory(),_data);
            if (k.getMult() > DataBase.MAX_MULT_FIGHT) {
                _data.setError(true);
            }
            if (k.getMult() < 1) {
                _data.setError(true);
            }
        }
        DataInfoChecker.checkPositiveOrZeroRates(multDamage.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStatisticFoe.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(multStatisticFoe.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStatistic.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(multStatistic.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),protectAgainstLowStat,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),forbiddenBoost,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),cancelChgtStatFoeTeam,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),cancelChgtStatTeam,_data);
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

    public void setCancelChgtStatFoeTeam(
            EnumList<Statistic> _cancelChgtStatFoeTeam) {
        cancelChgtStatFoeTeam = _cancelChgtStatFoeTeam;
    }

    public EnumList<Statistic> getCancelChgtStatTeam() {
        return cancelChgtStatTeam;
    }

    public void setCancelChgtStatTeam(EnumList<Statistic> _cancelChgtStatTeam) {
        cancelChgtStatTeam = _cancelChgtStatTeam;
    }

    public CategoryMults getMultDamage() {
        return multDamage;
    }

    public void setMultDamage(CategoryMults _multDamage) {
        multDamage = _multDamage;
    }

    public AbsMap<Statistic, Rate> getMultStatistic() {
        return multStatistic;
    }

    public void setMultStatistic(AbsMap<Statistic, Rate> _multStatistic) {
        multStatistic = _multStatistic;
    }

    public AbsMap<Statistic, Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }

    public void setMultStatisticFoe(AbsMap<Statistic, Rate> _multStatisticFoe) {
        multStatisticFoe = _multStatisticFoe;
    }

    public EnumList<Statistic> getProtectAgainstLowStat() {
        return protectAgainstLowStat;
    }

    public void setProtectAgainstLowStat(
            EnumList<Statistic> _protectAgainstLowStat) {
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
