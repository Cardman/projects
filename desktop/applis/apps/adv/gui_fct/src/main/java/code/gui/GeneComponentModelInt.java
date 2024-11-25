package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class GeneComponentModelInt {
    private final AbstractProgramInfos compoFactory;
    private AbsSpinner spinner;

    public GeneComponentModelInt(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    public AbsSpinner geneInt() {
        spinner = compoFactory.getCompoFactory().newSpinner(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        return spinner;
    }

    public Integer value() {
        return valueInt();
    }

    public int valueInt() {
        return getSpinner().getValue();
    }

    public void value(Integer _v) {
        valueInt(_v);
    }

    public int valueInt(int _v) {
        int p_ = spinner.getValue();
        spinner.setValue(_v);
        return p_;
    }

    public AbsSpinner getSpinner() {
        return spinner;
    }
}
