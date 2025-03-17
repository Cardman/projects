package cards.consts.beans;

import cards.consts.EndGameState;
import code.util.core.NumberUtil;

public final class TakerResult {

    private long differenceScoreTaker;

    private EndGameState winEqualityLoose;

    public void setDifferenceScoreTaker(long _d) {
        this.differenceScoreTaker = _d;
    }

    public void setWinEqualityLoose(EndGameState _w) {
        this.winEqualityLoose = _w;
    }

    public boolean win() {
        return winEqualityLoose == EndGameState.WIN;
    }

    public boolean equality() {
        return winEqualityLoose == EndGameState.EQUALLITY;
    }

    public boolean loose() {
        return winEqualityLoose == EndGameState.LOOSE;
    }

    public boolean successfulBid() {
        return differenceScoreTaker > 0;
    }

    public boolean midBid() {
        return differenceScoreTaker == 0;
    }

    public boolean failedBid() {
        return differenceScoreTaker < 0;
    }

    public long absoluteDiff() {
        return NumberUtil.abs(differenceScoreTaker);
    }

    public long getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }
}
