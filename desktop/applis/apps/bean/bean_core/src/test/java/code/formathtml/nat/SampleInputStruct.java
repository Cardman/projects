package code.formathtml.nat;

import code.bean.nat.CommNatStruct;

public final class SampleInputStruct extends CommNatStruct {
    private final SampleInput sampleInput;

    public SampleInputStruct(SampleInput _sampleInput) {
        super("simple.Input");
        sampleInput = _sampleInput;
    }

    public String getTypedString() {
        return sampleInput.getTypedString();
    }

    public void setTypedString(String _typedString) {
        sampleInput.setTypedString(_typedString);
    }
}
