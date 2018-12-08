package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public class ReservedMethod extends FoundErrorInterpret {

    private static final String STANDARD_METHOD = "reserved method";
    private MethodId methodeId;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),STANDARD_METHOD,
                SEP_KEY_VAL,methodeId.getSignature(),SEP_INFO);
    }

    public MethodId getMethodeId() {
        return methodeId;
    }

    public void setMethodeId(MethodId _methodeId) {
        methodeId = _methodeId;
    }
}
