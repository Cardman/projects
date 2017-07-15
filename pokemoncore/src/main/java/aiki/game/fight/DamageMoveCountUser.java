package aiki.game.fight;
import code.datacheck.CheckedData;
import code.maths.Rate;

@CheckedData
public class DamageMoveCountUser {

    private boolean criticalHit;

    private Rate damageCount;

    private Rate damage;

    private Rate damageClone;

    private byte hits;

    private boolean keepProcessing;

    public boolean isCriticalHit() {
        return criticalHit;
    }

    public void setCriticalHit(boolean _criticalHit) {
        criticalHit = _criticalHit;
    }

    public Rate getDamageCount() {
        return damageCount;
    }

    public void setDamageCount(Rate _damage) {
        damageCount = _damage;
    }

    public Rate getDamage() {
        return damage;
    }

    public void setDamage(Rate _damage) {
        damage = _damage;
    }

    public Rate getDamageClone() {
        return damageClone;
    }

    public void setDamageClone(Rate _damageClone) {
        damageClone = _damageClone;
    }

    public byte getHits() {
        return hits;
    }

    public void setHits(byte _hits) {
        hits = _hits;
    }

    public boolean isKeepProcessing() {
        return keepProcessing;
    }

    public void setKeepProcessing(boolean _keepProcessing) {
        keepProcessing = _keepProcessing;
    }
}
