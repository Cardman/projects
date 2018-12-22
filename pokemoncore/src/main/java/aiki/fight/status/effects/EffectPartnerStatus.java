package aiki.fight.status.effects;

import aiki.db.DataBase;
import code.maths.Rate;


public final class EffectPartnerStatus {

    private Rate multDamageAgainstFoe;

    private boolean weddingAlly;

    private Rate restoredHpRateLovedAlly;

    public void validate(DataBase _dataBase) {
        if (!multDamageAgainstFoe.isZeroOrGt()) {
            _dataBase.setError(true);
            return;

        }
        if (!restoredHpRateLovedAlly.isZeroOrGt()) {
            _dataBase.setError(true);
            return;

        }
    }

    public Rate getMultDamageAgainstFoe() {
        return multDamageAgainstFoe;
    }

    public void setMultDamageAgainstFoe(Rate _multDamageAgainstFoe) {
        multDamageAgainstFoe = _multDamageAgainstFoe;
    }

    public boolean getWeddingAlly() {
        return weddingAlly;
    }

    public void setWeddingAlly(boolean _weddingAlly) {
        weddingAlly = _weddingAlly;
    }

    public Rate getRestoredHpRateLovedAlly() {
        return restoredHpRateLovedAlly;
    }

    public void setRestoredHpRateLovedAlly(Rate _restoredHpRateLovedAlly) {
        restoredHpRateLovedAlly = _restoredHpRateLovedAlly;
    }
}
