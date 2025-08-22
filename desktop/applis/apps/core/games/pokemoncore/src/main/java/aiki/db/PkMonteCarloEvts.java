package aiki.db;

import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class PkMonteCarloEvts {
    private final CustList<LgInt> evts;
    private int index;

    public PkMonteCarloEvts(CustList<LgInt> _e) {
        this.evts = _e;
    }
    public static CustList<LgInt> parse(String _evts) {
        CustList<LgInt> evts_ = new CustList<LgInt>();
        StringBuilder str_ = new StringBuilder();
        int len_ = _evts.length();
        for (int i = 0; i < len_; i++) {
            char ch_ = _evts.charAt(i);
            if (NumberUtil.isDigit(ch_)) {
                str_.append(ch_);
            } else {
                addIfNotEmpty(evts_, str_);
                str_.delete(0,str_.length());
            }
        }
        addIfNotEmpty(evts_, str_);
        return evts_;
    }

    private static void addIfNotEmpty(CustList<LgInt> _evts, StringBuilder _str) {
        if (_str.length() > 0) {
            _evts.add(LgInt.newLgInt(_str.toString()));
        }
    }

    public LgInt tryIncr(){
        if (simulatedRoundByRound() && !evts.isEmpty()) {
            LgInt e_ = evts.get(index);
            index = (index + 1) % evts.size();
            return e_;
        }
        return null;
    }

    public boolean simulatedRoundByRound() {
        return evts != null;
    }
}
