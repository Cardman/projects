package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsProgressBar;
import code.gui.initialize.AbsCompoFactory;

public final class ProgressBarStruct extends CustComponentStruct {
    private final AbsProgressBar progressBar;
    public ProgressBarStruct(String _className, AbsCompoFactory _compoFactory) {
        super(_className);
        progressBar = _compoFactory.newAbsProgressBar();
    }

    public BooleanStruct isHorizontal() {
        return BooleanStruct.of(progressBar.isHorizontal());
    }

    public IntStruct getValue() {
        return new IntStruct(progressBar.getValue());
    }

    public IntStruct getMinimum() {
        return new IntStruct(progressBar.getMinimum());
    }

    public IntStruct getMaximum() {
        return new IntStruct(progressBar.getMaximum());
    }

    public void setValue(Struct _n) {
        progressBar.setValue(((NumberStruct)_n).intStruct());
    }

    public void setMinimum(Struct _n) {
        progressBar.setMinimum(((NumberStruct)_n).intStruct());
    }

    public void setMaximum(Struct _n) {
        progressBar.setMaximum(((NumberStruct)_n).intStruct());
    }

    public void setHorizontal(Struct _bool) {
        progressBar.setHorizontal(BooleanStruct.isTrue(_bool));
    }
    @Override
    protected AbsCustComponent getComponent() {
        return progressBar;
    }
}
