package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypesDuo;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.EnumList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public final class EffectGlobal extends Effect {

    @CheckedData
    private boolean priseEnComptePkLanceur;
    @CheckedData
    private boolean weather;
    @CheckedData
    private boolean canceledIfUsed;
    @CheckedData
    private boolean reverseOrderOfSortBySpeed;
    @CheckedData
    private boolean puttingKo;
    @CheckedData
    private Rate multAccuracy;
    @CheckedData
    private boolean unusableItem;
    private StringList preventStatus;

    private StringList immuneTypes;

    @CheckedData
    private Rate damageEndRound;

    @CheckedData
    private Rate healingEndRound;

    @CheckedData
    private Rate healingEndRoundGround;

    private ObjectMap<TypesDuo,Rate> efficiencyMoves;

    private StringList disableImmuAgainstTypes;
    private StringList cancelProtectingAbilities;
    private StringList unusableMoves;

    private StringMap<Rate> multDamagePrepaRound;

    private StringList movesUsedByTargetedFighters;

    @CheckedData
    private Rate multEffectLovingAlly;
    private StringMap<Rate> multPowerMoves;

    private ObjectMap<StatisticType,Rate> multStatIfContainsType;

    private StringList cancelEffects;
    private StringMap<Rate> multDamageTypesMoves;
    private EnumList<Statistic> cancelChgtStat;

    @CheckedData
    private String invokedMoveTerrain;

    @CheckedData
    private StringList changedTypesTerrain;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(cancelChgtStat)) {
            throw new DataException();
        }
        if (!_data.getTypes().containsAllObj(immuneTypes)) {
            throw new DataException();
        }
        if (!healingEndRound.isZeroOrGt()) {
            throw new DataException();
        }
        if (!healingEndRoundGround.isZeroOrGt()) {
            throw new DataException();
        }
        if (!damageEndRound.isZeroOrGt()) {
            throw new DataException();
        }
        for (String k:multDamagePrepaRound.getKeys()) {
            if (!_data.getTypes().containsObj(k)) {
                throw new DataException();
            }
            if (!multDamagePrepaRound.getVal(k).isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (String k:multDamageTypesMoves.getKeys()) {
            if (!_data.getTypes().containsObj(k)) {
                throw new DataException();
            }
            if (!multDamageTypesMoves.getVal(k).isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (String k:multPowerMoves.getKeys()) {
            if (!_data.getMoves().contains(k)) {
                throw new DataException();
            }
            if (!multPowerMoves.getVal(k).isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (TypesDuo k:efficiencyMoves.getKeys()) {
            if (!_data.getTypes().containsObj(k.getDamageType())) {
                throw new DataException();
            }
            if (!_data.getTypes().containsObj(k.getPokemonType())) {
                throw new DataException();
            }
            if (!efficiencyMoves.getVal(k).isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (StatisticType k:multStatIfContainsType.getKeys()) {
            if (!k.getStatistic().isBoost()) {
                throw new DataException();
            }
            if (!_data.getTypes().containsObj(k.getType())) {
                throw new DataException();
            }
            if (!multStatIfContainsType.getVal(k).isZeroOrGt()) {
                throw new DataException();
            }
        }
        if (!_data.getTypes().containsAllObj(disableImmuAgainstTypes)) {
            throw new DataException();
        }
        if (!_data.getAbilities().containsAllAsKeys(cancelProtectingAbilities)) {
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(preventStatus)) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(unusableMoves)) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(movesUsedByTargetedFighters)) {
            throw new DataException();
        }
        if (!multAccuracy.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multEffectLovingAlly.isZeroOrGt()) {
            throw new DataException();
        }
        if (!_data.getMovesEffectGlobal().containsAllObj(cancelEffects)) {
            throw new DataException();
        }
        if (!invokedMoveTerrain.isEmpty()) {
            if (!_data.getMoves().contains(invokedMoveTerrain)) {
                throw new DataException();
            }
        }
        if (!_data.getTypes().containsAllObj(changedTypesTerrain)) {
            throw new DataException();
        }
    }

    public boolean getPriseEnComptePkLanceur() {
        return priseEnComptePkLanceur;
    }
    public void setPriseEnComptePkLanceur(boolean _priseEnComptePkLanceur) {
        priseEnComptePkLanceur = _priseEnComptePkLanceur;
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

    public ObjectMap<TypesDuo,Rate> getEfficiencyMoves() {
        return efficiencyMoves;
    }

    public void setEfficiencyMoves(ObjectMap<TypesDuo,Rate> _efficiencyMoves) {
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

    public void setCancelProtectingAbilities(StringList _cancelProtectingAbilities) {
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

    public void setMovesUsedByTargetedFighters(StringList _movesUsedByTargetedFighters) {
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
    public ObjectMap<StatisticType,Rate> getMultStatIfContainsType() {
        return multStatIfContainsType;
    }
    public void setMultStatIfContainsType(ObjectMap<StatisticType,Rate> _multStatIfContainsType) {
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
