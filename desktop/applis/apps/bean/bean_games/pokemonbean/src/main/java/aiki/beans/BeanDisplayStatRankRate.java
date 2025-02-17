package aiki.beans;

public final class BeanDisplayStatRankRate implements BeanDisplay<StatRankRate> {
    private final boolean rate;

    public BeanDisplayStatRankRate(boolean _r) {
        this.rate = _r;
    }

    @Override
    public int display(CommonBean _rend, StatRankRate _info, int _index) {
        _rend.formatMessageDirCts(Long.toString(_info.getRank()));
        _rend.formatMessageDirCts(_info.getFail());
        if (rate) {
            _rend.formatMessageDirCts(_info.getRate());
            return 3;
        }
        return 2;
    }
}
