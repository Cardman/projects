package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;

public final class PairMatchCast {
    private final AnaClassArgumentMatching match;
    private final String from;

    public PairMatchCast(AnaClassArgumentMatching _match, String _from) {
        this.match = _match;
        this.from = _from;
    }

    public AnaClassArgumentMatching getMatch() {
        return match;
    }

    public String getFrom() {
        return from;
    }
}
