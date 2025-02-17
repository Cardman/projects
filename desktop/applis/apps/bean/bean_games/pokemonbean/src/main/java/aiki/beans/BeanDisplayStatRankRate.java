package aiki.beans;

public final class BeanDisplayStatRankRate implements BeanDisplay<StatRankRate> {
    private final boolean boost;
    private final boolean rate;

    public BeanDisplayStatRankRate(boolean _b,boolean _r) {
        this.boost = _b;
        this.rate = _r;
    }

    @Override
    public int display(CommonBean _rend, StatRankRate _info, int _index) {
        int c_ = 0;
        if (boost) {
            _rend.formatMessageDirCts(Long.toString(_info.getRank()));
            c_++;
        }
        _rend.formatMessageDirCts(_info.getFail());
        c_++;
        if (rate) {
            _rend.formatMessageDirCts(_info.getRate().toNumberString());
            c_++;
            return c_;
        }
        return c_;
    }
}
