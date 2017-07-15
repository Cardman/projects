package aiki.game.fight.util;
import code.util.ints.Equallable;

public final class RandomBoolResults implements Equallable<RandomBoolResults> {

    private final boolean successful;

    private final boolean effectIfFail;

    public RandomBoolResults(boolean _successful, boolean _effectIfFail) {
        successful = _successful;
        effectIfFail = _effectIfFail;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public boolean isEffectIfFail() {
        return effectIfFail;
    }

    @Override
    public boolean eq(RandomBoolResults _g) {
        if (successful != _g.successful) {
            return false;
        }
        if (effectIfFail != _g.effectIfFail) {
            return false;
        }
        return true;
    }
}
