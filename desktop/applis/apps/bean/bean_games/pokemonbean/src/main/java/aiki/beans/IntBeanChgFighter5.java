package aiki.beans;

public final class IntBeanChgFighter5 {
    private final IntBeanChgList<String> lastSufferedMoveTypes;
    private final IntBeanChgList<String> alreadyInvokedMovesRound;
    private final IntBeanChgString lastSuccessfulMove;
    private final IntBeanChgLgInt nbRepeatingSuccessfulMoves;
    private final IntBeanChgBool usingItem;
    private final IntBeanChgString usedMoveLastRound;
    private final IntBeanChgString lastUsedMove;

    public IntBeanChgFighter5(IntBeanChgList<String> _n, IntBeanChgList<String> _i, IntBeanChgString _g, IntBeanChgLgInt _w, IntBeanChgBool _h, IntBeanChgString _c, IntBeanChgString _e) {
        this.lastSufferedMoveTypes = _n;
        this.alreadyInvokedMovesRound = _i;
        this.lastSuccessfulMove = _g;
        this.nbRepeatingSuccessfulMoves = _w;
        this.usingItem = _h;
        this.usedMoveLastRound = _c;
        this.lastUsedMove = _e;
    }

    public IntBeanChgList<String> getLastSufferedMoveTypes() {
        return lastSufferedMoveTypes;
    }

    public IntBeanChgList<String> getAlreadyInvokedMovesRound() {
        return alreadyInvokedMovesRound;
    }

    public IntBeanChgString getLastSuccessfulMove() {
        return lastSuccessfulMove;
    }

    public IntBeanChgLgInt getNbRepeatingSuccessfulMoves() {
        return nbRepeatingSuccessfulMoves;
    }

    public IntBeanChgBool getUsingItem() {
        return usingItem;
    }

    public IntBeanChgString getUsedMoveLastRound() {
        return usedMoveLastRound;
    }

    public IntBeanChgString getLastUsedMove() {
        return lastUsedMove;
    }
}
