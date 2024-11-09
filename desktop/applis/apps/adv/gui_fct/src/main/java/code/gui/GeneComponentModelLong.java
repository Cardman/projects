package code.gui;

import code.gui.initialize.*;

public final class GeneComponentModelLong implements GeneComponentModel<Long> {
    private final AbstractProgramInfos compoFactory;
    private AbsSpinner textLong;
    public GeneComponentModelLong(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        return gene(0L);
    }

    public AbsCustComponent gene(long _d) {
        textLong = compoFactory.getCompoFactory().newSpinner(_d, Long.MIN_VALUE,Long.MAX_VALUE,1);
        return textLong;
    }

    @Override
    public Long value() {
        return valueLong();
    }

    public long valueLong() {
        return textLong.valueLong();
    }

    @Override
    public void value(Long _v) {
        valueLong(_v);
    }

    public long valueLong(long _v) {
        long p_ = textLong.valueLong();
        textLong.setValue(_v);
        return p_;
    }
}
