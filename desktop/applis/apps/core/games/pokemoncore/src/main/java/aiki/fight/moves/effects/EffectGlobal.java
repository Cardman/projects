package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.StatisticType;
import aiki.fight.util.StatisticTypeList;
import aiki.fight.util.TypesDuos;
import aiki.fight.util.TypesDuo;
import code.maths.Rate;
import code.util.*;
import code.util.core.StringUtil;


public final class EffectGlobal extends Effect {

    private boolean weather;
    private boolean canceledIfUsed;
    private boolean reverseOrderOfSortBySpeed;
    private boolean puttingKo;
    private Rate multAccuracy;
    private boolean unusableItem;
    private StringList preventStatus;

    private StringList immuneTypes;

    private Rate damageEndRound;

    private Rate healingEndRound;

    private Rate healingEndRoundGround;

    private TypesDuos efficiencyMoves;

    private StringList disableImmuAgainstTypes;
    private StringList cancelProtectingAbilities;
    private StringList unusableMoves;

    private StringMap<Rate> multDamagePrepaRound;

    private StringList movesUsedByTargetedFighters;

    private Rate multEffectLovingAlly;
    private StringMap<Rate> multPowerMoves;

    private StatisticTypeList<Rate> multStatIfContainsType;

    private StringList cancelEffects;
    private StringMap<Rate> multDamageTypesMoves;
    private EnumList<Statistic> cancelChgtStat;

    private String invokedMoveTerrain;

    private StringList changedTypesTerrain;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelChgtStat)) {
            _data.setError(true);
        }
        if (!_data.getTypes().containsAllObj(immuneTypes)) {
            _data.setError(true);
        }
        if (!healingEndRound.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!healingEndRoundGround.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!damageEndRound.isZeroOrGt()) {
            _data.setError(true);
        }
        for (String k : multDamagePrepaRound.getKeys()) {
            if (!StringUtil.contains(_data.getTypes(), k)) {
                _data.setError(true);
            }
            if (!multDamagePrepaRound.getVal(k).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        for (String k : multDamageTypesMoves.getKeys()) {
            if (!StringUtil.contains(_data.getTypes(), k)) {
                _data.setError(true);
            }
            if (!multDamageTypesMoves.getVal(k).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        for (String k : multPowerMoves.getKeys()) {
            if (!_data.getMoves().contains(k)) {
                _data.setError(true);
            }
            if (!multPowerMoves.getVal(k).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        for (TypesDuo k : efficiencyMoves.getKeys()) {
            if (!StringUtil.contains(_data.getTypes(), k.getDamageType())) {
                _data.setError(true);
            }
            if (!StringUtil.contains(_data.getTypes(), k.getPokemonType())) {
                _data.setError(true);
            }
            if (!efficiencyMoves.getVal(k).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        for (StatisticType k : multStatIfContainsType.getKeys()) {
            if (!k.getStatistic().isBoost()) {
                _data.setError(true);
            }
            if (!StringUtil.contains(_data.getTypes(), k.getType())) {
                _data.setError(true);
            }
            if (!multStatIfContainsType.getVal(k).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        if (!_data.getTypes().containsAllObj(disableImmuAgainstTypes)) {
            _data.setError(true);
        }
        if (!_data.getAbilities().containsAllAsKeys(cancelProtectingAbilities)) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(preventStatus)) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(unusableMoves)) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(movesUsedByTargetedFighters)) {
            _data.setError(true);
        }
        if (!multAccuracy.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multEffectLovingAlly.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!_data.getMovesEffectGlobal().containsAllObj(cancelEffects)) {
            _data.setError(true);
        }
        if (!invokedMoveTerrain.isEmpty()) {
            if (!_data.getMoves().contains(invokedMoveTerrain)) {
                _data.setError(true);
            }
        }
        if (!_data.getTypes().containsAllObj(changedTypesTerrain)) {
            _data.setError(true);

        }
    }

    public boolean getWeather() {
        return weather;
    }

    public void setWeather(boolean _weather) {
        weather = _weather;
    }

    public boolean getCanceledIfUsed() {
        return canceledIfUsed;
    }

    public void setCanceledIfUsed(boolean _canceledIfUsed) {
        canceledIfUsed = _canceledIfUsed;
    }

    public boolean getReverseOrderOfSortBySpeed() {
        return reverseOrderOfSortBySpeed;
    }

    public void setReverseOrderOfSortBySpeed(boolean _reverseOrderOfSortBySpeed) {
        reverseOrderOfSortBySpeed = _reverseOrderOfSortBySpeed;
    }

    public boolean getPuttingKo() {
        return puttingKo;
    }

    public void setPuttingKo(boolean _puttingKo) {
        puttingKo = _puttingKo;
    }

    public Rate getMultAccuracy() {
        return multAccuracy;
    }

    public void setMultAccuracy(Rate _multAccuracy) {
        multAccuracy = _multAccuracy;
    }

    public boolean getUnusableItem() {
        return unusableItem;
    }

    public void setUnusableItem(boolean _unusableItem) {
        unusableItem = _unusableItem;
    }

    public StringList getPreventStatus() {
        return preventStatus;
    }

    public void setPreventStatus(StringList _preventStatus) {
        preventStatus = _preventStatus;
    }

    public StringList getImmuneTypes() {
        return immuneTypes;
    }

    public void setImmuneTypes(StringList _immuneTypes) {
        immuneTypes = _immuneTypes;
    }

    public Rate getDamageEndRound() {
        return damageEndRound;
    }

    public void setDamageEndRound(Rate _damageEndRound) {
        damageEndRound = _damageEndRound;
    }

    public Rate getHealingEndRound() {
        return healingEndRound;
    }

    public void setHealingEndRound(Rate _healingEndRound) {
        healingEndRound = _healingEndRound;
    }

    public Rate getHealingEndRoundGround() {
        return healingEndRoundGround;
    }

    public void setHealingEndRoundGround(Rate _healingEndRoundGround) {
        healingEndRoundGround = _healingEndRoundGround;
    }

    public TypesDuos getEfficiencyMoves() {
        return efficiencyMoves;
    }

    public void setEfficiencyMoves(TypesDuos _efficiencyMoves) {
        efficiencyMoves = _efficiencyMoves;
    }

    public StringList getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public void setDisableImmuAgainstTypes(StringList _disableImmuAgainstTypes) {
        disableImmuAgainstTypes = _disableImmuAgainstTypes;
    }

    public StringList getCancelProtectingAbilities() {
        return cancelProtectingAbilities;
    }

    public void setCancelProtectingAbilities(
            StringList _cancelProtectingAbilities) {
        cancelProtectingAbilities = _cancelProtectingAbilities;
    }

    public StringList getUnusableMoves() {
        return unusableMoves;
    }

    public void setUnusableMoves(StringList _unusableMoves) {
        unusableMoves = _unusableMoves;
    }

    public StringMap<Rate> getMultDamagePrepaRound() {
        return multDamagePrepaRound;
    }

    public void setMultDamagePrepaRound(StringMap<Rate> _multDamagePrepaRound) {
        multDamagePrepaRound = _multDamagePrepaRound;
    }

    public StringList getMovesUsedByTargetedFighters() {
        return movesUsedByTargetedFighters;
    }

    public void setMovesUsedByTargetedFighters(
            StringList _movesUsedByTargetedFighters) {
        movesUsedByTargetedFighters = _movesUsedByTargetedFighters;
    }

    public Rate getMultEffectLovingAlly() {
        return multEffectLovingAlly;
    }

    public void setMultEffectLovingAlly(Rate _multEffectLovingAlly) {
        multEffectLovingAlly = _multEffectLovingAlly;
    }

    public StringMap<Rate> getMultPowerMoves() {
        return multPowerMoves;
    }

    public void setMultPowerMoves(StringMap<Rate> _multPowerMoves) {
        multPowerMoves = _multPowerMoves;
    }

    public StatisticTypeList<Rate> getMultStatIfContainsType() {
        return multStatIfContainsType;
    }

    public void setMultStatIfContainsType(
            StatisticTypeList<Rate> _multStatIfContainsType) {
        multStatIfContainsType = _multStatIfContainsType;
    }

    public StringList getCancelEffects() {
        return cancelEffects;
    }

    public void setCancelEffects(StringList _cancelEffects) {
        cancelEffects = _cancelEffects;
    }

    public StringMap<Rate> getMultDamageTypesMoves() {
        return multDamageTypesMoves;
    }

    public void setMultDamageTypesMoves(StringMap<Rate> _multDamageTypesMoves) {
        multDamageTypesMoves = _multDamageTypesMoves;
    }

    public EnumList<Statistic> getCancelChgtStat() {
        return cancelChgtStat;
    }

    public void setCancelChgtStat(EnumList<Statistic> _cancelChgtStat) {
        cancelChgtStat = _cancelChgtStat;
    }

    public String getInvokedMoveTerrain() {
        return invokedMoveTerrain;
    }

    public void setInvokedMoveTerrain(String _invokedMoveTerrain) {
        invokedMoveTerrain = _invokedMoveTerrain;
    }

    public StringList getChangedTypesTerrain() {
        return changedTypesTerrain;
    }

    public void setChangedTypesTerrain(StringList _changedTypesTerrain) {
        changedTypesTerrain = _changedTypesTerrain;
    }
}
