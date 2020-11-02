package code.formathtml.nat;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class SampleInputStruct extends WithoutParentIdStruct {
    private final SampleInput sampleInput;

    public SampleInputStruct(SampleInput _sampleInput) {
        sampleInput = _sampleInput;
    }

    public String getTypedString() {
        return sampleInput.getTypedString();
    }

    public void setTypedString(String _typedString) {
        sampleInput.setTypedString(_typedString);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return "simple.Input";
    }
}
