package code.gui;

import code.gui.initialize.*;
import code.maths.*;

public final class GeneComponentModelRate implements GeneComponentModel<Rate> {
    private final AbstractProgramInfos compoFactory;
    private AbsTextField textRate;
    public GeneComponentModelRate(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        return geneRate(Rate.zero());
    }

    public AbsCustComponent geneRate(Rate _d) {
        textRate = compoFactory.getCompoFactory().newTextField(_d.toNumberString());
        return getTextRate();
    }
    @Override
    public Rate value() {
        return valueRate();
    }
    public Rate valueRate() {
        return Rate.newRate(getTextRate().getText());
    }
    @Override
    public void value(Rate _v) {
        valueRate(_v);
    }
    public Rate valueRate(Rate _v) {
        String p_ = getTextRate().getText();
        getTextRate().setText(_v.toNumberString());
        return Rate.newRate(p_);
    }

    public AbsTextField getTextRate() {
        return textRate;
    }
}
