package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.StatisticTypeList;
import aiki.fight.util.TypesDuos;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.EnumList;
import code.util.StringList;
import code.util.StringMap;


public final class EffectGlobal extends Effect {

    private final EffectGlobalCore effectGlobalCore = new EffectGlobalCore();

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
        getEffectGlobalCore().validate(_data);
        DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),cancelChgtStat,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),multDamagePrepaRound.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(multDamagePrepaRound.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),multDamageTypesMoves.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(multDamageTypesMoves.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),multPowerMoves.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(multPowerMoves.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),efficiencyMoves.getTypes(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(efficiencyMoves.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStatIfContainsType.getStatistics(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),multStatIfContainsType.getTypes(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(multStatIfContainsType.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),disableImmuAgainstTypes,_data);
        DataInfoChecker.checkStringListContains(_data.getAbilities().getKeys(),cancelProtectingAbilities,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),unusableMoves,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),movesUsedByTargetedFighters,_data);
        DataInfoChecker.checkPositiveOrZero(multEffectLovingAlly,_data);
        DataInfoChecker.checkStringListContains(_data.getMovesEffectGlobal(),cancelEffects,_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getMoves().getKeys(),invokedMoveTerrain,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),changedTypesTerrain,_data);
    }

    public boolean getWeather() {
        return effectGlobalCore.getWeather();
    }

    public void setWeather(boolean _weather) {
        effectGlobalCore.setWeather(_weather);
    }

    public boolean getCanceledIfUsed() {
        return effectGlobalCore.getCanceledIfUsed();
    }

    public void setCanceledIfUsed(boolean _canceledIfUsed) {
        effectGlobalCore.setCanceledIfUsed(_canceledIfUsed);
    }

    public boolean getReverseOrderOfSortBySpeed() {
        return effectGlobalCore.getReverseOrderOfSortBySpeed();
    }

    public void setReverseOrderOfSortBySpeed(boolean _reverseOrderOfSortBySpeed) {
        effectGlobalCore.setReverseOrderOfSortBySpeed(_reverseOrderOfSortBySpeed);
    }

    public boolean getPuttingKo() {
        return effectGlobalCore.getPuttingKo();
    }

    public void setPuttingKo(boolean _puttingKo) {
        effectGlobalCore.setPuttingKo(_puttingKo);
    }

    public Rate getMultAccuracy() {
        return effectGlobalCore.getMultAccuracy();
    }

    public void setMultAccuracy(Rate _multAccuracy) {
        effectGlobalCore.setMultAccuracy(_multAccuracy);
    }

    public boolean getUnusableItem() {
        return effectGlobalCore.getUnusableItem();
    }

    public void setUnusableItem(boolean _unusableItem) {
        effectGlobalCore.setUnusableItem(_unusableItem);
    }

    public StringList getPreventStatus() {
        return effectGlobalCore.getPreventStatus();
    }

    public void setPreventStatus(StringList _preventStatus) {
        effectGlobalCore.setPreventStatus(_preventStatus);
    }

    public StringList getImmuneTypes() {
        return effectGlobalCore.getImmuneTypes();
    }

    public void setImmuneTypes(StringList _immuneTypes) {
        effectGlobalCore.setImmuneTypes(_immuneTypes);
    }

    public Rate getDamageEndRound() {
        return effectGlobalCore.getDamageEndRound();
    }

    public void setDamageEndRound(Rate _damageEndRound) {
        effectGlobalCore.setDamageEndRound(_damageEndRound);
    }

    public Rate getHealingEndRound() {
        return effectGlobalCore.getHealingEndRound();
    }

    public void setHealingEndRound(Rate _healingEndRound) {
        effectGlobalCore.setHealingEndRound(_healingEndRound);
    }

    public Rate getHealingEndRoundGround() {
        return effectGlobalCore.getHealingEndRoundGround();
    }

    public void setHealingEndRoundGround(Rate _healingEndRoundGround) {
        effectGlobalCore.setHealingEndRoundGround(_healingEndRoundGround);
    }

    public EffectGlobalCore getEffectGlobalCore() {
        return effectGlobalCore;
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
