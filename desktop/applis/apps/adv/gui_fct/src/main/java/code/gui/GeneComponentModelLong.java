package code.gui;

import code.gui.initialize.*;

public final class GeneComponentModelLong {
    private final AbstractProgramInfos compoFactory;
    private AbsSpinner textLong;
    public GeneComponentModelLong(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    public AbsCustComponent geneLong() {
        textLong = compoFactory.getCompoFactory().newSpinner(0, Long.MIN_VALUE,Long.MAX_VALUE,1);
        return textLong;
    }

    public Long value() {
        return valueLong();
    }

    public long valueLong() {
        return getTextLong().valueLong();
    }

    public void value(Long _v) {
        valueLong(_v);
    }

    public long valueLong(long _v) {
        long p_ = textLong.valueLong();
        textLong.setValue(_v);
        return p_;
    }

    public AbsSpinner getTextLong() {
        return textLong;
    }
}
