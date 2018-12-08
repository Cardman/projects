package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public class DuplicateParamMethod extends FoundErrorInterpret {

    private static final String COMMON_SIGNATURE = "common signature";

    private static final String LOCATION = "location";

    private String commonSignature;

    private String otherType;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,COMMON_SIGNATURE,SEP_KEY_VAL,commonSignature,
                SEP_INFO,LOCATION,SEP_KEY_VAL,otherType);
    }

    public void setCommonSignature(String _commonSignature) {
        commonSignature = _commonSignature;
    }

    public void setOtherType(String _otherType) {
        otherType = _otherType;
    }
}
