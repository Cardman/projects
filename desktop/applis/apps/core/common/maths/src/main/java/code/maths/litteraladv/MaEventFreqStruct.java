package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.montecarlo.EventFreq;
import code.util.CustList;

public final class MaEventFreqStruct extends MaListNbStruct {
    private final EventFreq<Rate> pair;

    public MaEventFreqStruct(EventFreq<Rate> _pair) {
        this.pair = _pair;
    }

    public EventFreq<Rate> getPair() {
        return pair;
    }

    @Override
    public CustList<Rate> getNumberList() {
        return new CustList<Rate>(pair.getEvent(),new Rate(pair.getFreq()));
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaEventFreqStruct)) {
            return false;
        }
        return eq(pair,((MaEventFreqStruct)_other).pair);
    }

    static boolean eq(EventFreq<Rate> _this, EventFreq<Rate> _other) {
        return _this.getEvent().eq(_other.getEvent()) && _this.getFreq().eq(_other.getFreq());
    }
    @Override
    public String displayRsult() {
        return exportStr(pair);
    }

    static String exportStr(EventFreq<Rate> _pair) {
        return _pair.getEvent().toNumberString() + MaOperationNode.EVT + _pair.getFreq().toNumberString();
    }
}
