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
    public AbsCustComponent gene() {
        return gene(Rate.zero());
    }

    @Override
    public AbsCustComponent gene(Rate _d) {
        textRate = compoFactory.getCompoFactory().newTextField(_d.toNumberString());
        return textRate;
    }

    @Override
    public Rate value() {
        return Rate.newRate(textRate.getText());
    }

    @Override
    public Rate value(Rate _v) {
        String p_ = textRate.getText();
        textRate.setText(_v.toNumberString());
        return Rate.newRate(p_);
    }
}
