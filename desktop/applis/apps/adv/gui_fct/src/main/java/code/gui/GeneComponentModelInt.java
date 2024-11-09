package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class GeneComponentModelInt implements GeneComponentModel<Integer> {
    private final AbstractProgramInfos compoFactory;
    private AbsSpinner spinner;

    public GeneComponentModelInt(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        return geneInt();
    }

    public AbsSpinner geneInt() {
        spinner = compoFactory.getCompoFactory().newSpinner(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        return spinner;
    }

    @Override
    public Integer value() {
        return valueInt();
    }

    public int valueInt() {
        return spinner.getValue();
    }

    @Override
    public void value(Integer _v) {
        valueInt(_v);
    }

    public int valueInt(int _v) {
        int p_ = spinner.getValue();
        spinner.setValue(_v);
        return p_;
    }
}
