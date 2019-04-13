package aiki.game.fight.util;

public final class RandomBoolResults {

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
}
