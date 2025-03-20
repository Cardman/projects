package cards.consts.beans;

import cards.consts.EndGameState;
import cards.consts.LineDeal;
import code.bean.IntBeanBuilderHelperCommon;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;

public final class TakerResult {

    private long differenceScoreTaker;

    private EndGameState winEqualityLoose;

    public static int buildScores(IntBeanBuilderHelperCommon _builder, StringList _nicknames, CustList<LineDeal> _lines) {
        _builder.initGrid();
        _builder.colCount(_nicknames.size()+1);
        _builder.formatMessageDirCts("");
        for (String s:_nicknames) {
            _builder.formatMessageDirCts(s);
        }
        for (LineDeal l: _lines) {
            _builder.formatMessageDirCts(Long.toString(l.getNumber()));
            for (long s: l.getScores()) {
                _builder.formatMessageDirCts(Long.toString(s));
            }
        }
        _builder.feedParents();
        return 0;
    }
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
