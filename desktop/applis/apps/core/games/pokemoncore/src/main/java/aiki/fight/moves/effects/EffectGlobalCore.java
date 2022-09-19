package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.StringList;

public final class EffectGlobalCore {

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
    public void validate(DataBase _data) {
        DataInfoChecker.checkStringListContains(_data.getTypes(),immuneTypes,_data);
        DataInfoChecker.checkPositiveOrZero(healingEndRound,_data);
        DataInfoChecker.checkPositiveOrZero(healingEndRoundGround,_data);
        DataInfoChecker.checkPositiveOrZero(damageEndRound,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),preventStatus,_data);
        DataInfoChecker.checkPositiveOrZero(multAccuracy,_data);
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

}
