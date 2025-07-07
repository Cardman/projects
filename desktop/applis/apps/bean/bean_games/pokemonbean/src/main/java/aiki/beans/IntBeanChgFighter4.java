package aiki.beans;

public final class IntBeanChgFighter4 {
    private final IntBeanChgString usedBallCatching;
    private final IntBeanChgLong level;
    private final IntBeanChgLong happiness;
    private final IntBeanChgLong nbPrepaRound;
    private final IntBeanChgBool disappeared;
    private final IntBeanChgBool needingToRecharge;
    private final IntBeanChgString lastSufferedMove;

    public IntBeanChgFighter4(IntBeanChgString _n, IntBeanChgLong _i, IntBeanChgLong _g, IntBeanChgLong _w, IntBeanChgBool _h, IntBeanChgBool _c, IntBeanChgString _e) {
        this.usedBallCatching = _n;
        this.level = _i;
        this.happiness = _g;
        this.nbPrepaRound = _w;
        this.disappeared = _h;
        this.needingToRecharge = _c;
        this.lastSufferedMove = _e;
    }

    public IntBeanChgString getUsedBallCatching() {
        return usedBallCatching;
    }

    public IntBeanChgLong getLevel() {
        return level;
    }

    public IntBeanChgLong getHappiness() {
        return happiness;
    }

    public IntBeanChgLong getNbPrepaRound() {
        return nbPrepaRound;
    }

    public IntBeanChgBool getDisappeared() {
        return disappeared;
    }

    public IntBeanChgBool getNeedingToRecharge() {
        return needingToRecharge;
    }

    public IntBeanChgString getLastSufferedMove() {
        return lastSufferedMove;
    }
}
