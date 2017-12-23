package code.expressionlanguage.methods.util;

public class DuplicateParamMethod extends FoundErrorInterpret {

    private static final String COMMON_SIGNATURE = "common signature";

    private static final String LOCATION = "location";

    private String commonSignature;

    private String otherType;

    @Override
    public String display() {
        return super.display()+SEP_INFO+COMMON_SIGNATURE+SEP_KEY_VAL+commonSignature
                +SEP_INFO+LOCATION+SEP_KEY_VAL+otherType;
    }

    public void setCommonSignature(String _commonSignature) {
        commonSignature = _commonSignature;
    }

    public void setOtherType(String _otherType) {
        otherType = _otherType;
    }
}
