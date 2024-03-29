package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class GeneComponentModelInt implements GeneComponentModel<Integer> {
    private final AbstractProgramInfos compoFactory;
    private AbsSpinner spinner;

    public GeneComponentModelInt(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    @Override
    public AbsCustComponent gene() {
        spinner = compoFactory.getCompoFactory().newSpinner(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        return spinner;
    }

    @Override
    public AbsCustComponent gene(Integer _d) {
        spinner = compoFactory.getCompoFactory().newSpinner(_d, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        return spinner;
    }

    @Override
    public Integer value() {
        return spinner.getValue();
    }

    @Override
    public Integer value(Integer _v) {
        int p_ = spinner.getValue();
        spinner.setValue(_v);
        return p_;
    }
}
