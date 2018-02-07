package aiki.fight.status.effects;
import aiki.exceptions.DataException;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class EffectPartnerStatus {

    private Rate multDamageAgainstFoe;

    private boolean weddingAlly;

    private Rate restoredHpRateLovedAlly;

    public void validate() {
        if (!multDamageAgainstFoe.isZeroOrGt()) {
            throw new DataException();
        }
        if (!restoredHpRateLovedAlly.isZeroOrGt()) {
            throw new DataException();
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
