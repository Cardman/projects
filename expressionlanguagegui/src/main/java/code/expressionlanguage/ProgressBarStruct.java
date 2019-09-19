package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.ProgressBar;

public final class ProgressBarStruct extends CustComponentStruct {
    private ProgressBar progressBar = new ProgressBar();
    protected ProgressBarStruct(String _className) {
        super(_className);
    }

    public BooleanStruct isHorizontal() {
        return new BooleanStruct(progressBar.isHorizontal());
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

    public void setValue(Struct n) {
        progressBar.setValue(((NumberStruct)n).intStruct());
    }

    public void setMinimum(Struct n) {
        progressBar.setMinimum(((NumberStruct)n).intStruct());
    }

    public void setMaximum(Struct n) {
        progressBar.setMaximum(((NumberStruct)n).intStruct());
    }

    public void setHorizontal(Struct _bool) {
        progressBar.setHorizontal(((BooleanStruct)_bool).getInstance());
    }
    @Override
    protected CustComponent getComponent() {
        return progressBar;
    }
}
