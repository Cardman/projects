package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.CategoryMult;
import aiki.fight.util.CategoryMults;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.IdMap;
import code.util.IdList;
import code.util.StringList;


public final class EffectTeam extends Effect {

    private boolean forbiddingHealing;
    private IdList<Statistic> forbiddenBoost;
    private StringList unusableMoves;
    private IdList<Statistic> cancelChgtStatFoeTeam;
    private IdList<Statistic> cancelChgtStatTeam;

    private CategoryMults multDamage;

    private IdMap<Statistic, Rate> multStatistic;
    private IdMap<Statistic, Rate> multStatisticFoe;
    private IdList<Statistic> protectAgainstLowStat;
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
            DataInfoChecker.checkGreater(DataBase.MAX_MULT_FIGHT, k.getMult(),_data);
            DataInfoChecker.checkLower(1,k.getMult(),_data);
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

    public IdList<Statistic> getForbiddenBoost() {
        return forbiddenBoost;
    }

    public void setForbiddenBoost(IdList<Statistic> _forbiddenBoost) {
        forbiddenBoost = _forbiddenBoost;
    }

    public StringList getUnusableMoves() {
        return unusableMoves;
    }

    public void setUnusableMoves(StringList _unusableMoves) {
        unusableMoves = _unusableMoves;
    }

    public IdList<Statistic> getCancelChgtStatFoeTeam() {
        return cancelChgtStatFoeTeam;
    }

    public void setCancelChgtStatFoeTeam(
            IdList<Statistic> _cancelChgtStatFoeTeam) {
        cancelChgtStatFoeTeam = _cancelChgtStatFoeTeam;
    }

    public IdList<Statistic> getCancelChgtStatTeam() {
        return cancelChgtStatTeam;
    }

    public void setCancelChgtStatTeam(IdList<Statistic> _cancelChgtStatTeam) {
        cancelChgtStatTeam = _cancelChgtStatTeam;
    }

    public CategoryMults getMultDamage() {
        return multDamage;
    }

    public void setMultDamage(CategoryMults _multDamage) {
        multDamage = _multDamage;
    }

    public IdMap<Statistic, Rate> getMultStatistic() {
        return multStatistic;
    }

    public void setMultStatistic(IdMap<Statistic, Rate> _multStatistic) {
        multStatistic = _multStatistic;
    }

    public IdMap<Statistic, Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }

    public void setMultStatisticFoe(IdMap<Statistic, Rate> _multStatisticFoe) {
        multStatisticFoe = _multStatisticFoe;
    }

    public IdList<Statistic> getProtectAgainstLowStat() {
        return protectAgainstLowStat;
    }

    public void setProtectAgainstLowStat(
            IdList<Statistic> _protectAgainstLowStat) {
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
