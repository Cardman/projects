package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.CategoryMult;
import aiki.fight.util.CategoryMults;
import code.maths.Rate;
import code.util.*;
import code.util.core.StringUtil;


public final class EffectTeam extends Effect {

    private boolean forbiddingHealing;
    private EnumList<Statistic> forbiddenBoost;
    private StringList unusableMoves;
    private EnumList<Statistic> cancelChgtStatFoeTeam;
    private EnumList<Statistic> cancelChgtStatTeam;

    private CategoryMults multDamage;

    private EnumMap<Statistic, Rate> multStatistic;
    private EnumMap<Statistic, Rate> multStatisticFoe;
    private EnumList<Statistic> protectAgainstLowStat;
    private boolean protectAgainstCh;
    private StringList protectAgainstStatus;
    private StringList disableFoeTeamEffects;
    private StringList disableFoeTeamStatus;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(unusableMoves)) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(protectAgainstStatus)) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(disableFoeTeamEffects)) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(disableFoeTeamStatus)) {
            _data.setError(true);
        }
        for (CategoryMult k : multDamage.getKeys()) {
            if (!StringUtil.contains(_data.getCategories(), k.getCategory())) {
                _data.setError(true);
            }
            if (k.getMult() > DataBase.MAX_MULT_FIGHT) {
                _data.setError(true);
            }
            if (k.getMult() < 1) {
                _data.setError(true);
            }
            if (!multDamage.getVal(k).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        for (Statistic k : multStatisticFoe.getKeys()) {
            if (!k.isBoost()) {
                _data.setError(true);
            }
            if (!multStatisticFoe.getVal(k).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        for (Statistic k : multStatistic.getKeys()) {
            if (!k.isBoost()) {
                _data.setError(true);
            }
            if (!multStatistic.getVal(k).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                protectAgainstLowStat)) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(forbiddenBoost)) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                cancelChgtStatFoeTeam)) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                cancelChgtStatTeam)) {
            _data.setError(true);

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

    public EnumMap<Statistic, Rate> getMultStatistic() {
        return multStatistic;
    }

    public void setMultStatistic(EnumMap<Statistic, Rate> _multStatistic) {
        multStatistic = _multStatistic;
    }

    public EnumMap<Statistic, Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }

    public void setMultStatisticFoe(EnumMap<Statistic, Rate> _multStatisticFoe) {
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
