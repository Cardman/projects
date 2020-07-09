package code.expressionlanguage.exec.types;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.FormattedMethodId;
import code.util.CustList;

public final class OverridingMethod {
    private final FormattedMethodId formattedMethodId;
    private CustList<ClassMethodId> methodIds = new CustList<ClassMethodId>();

    public OverridingMethod(FormattedMethodId formattedMethodId) {
        this.formattedMethodId = formattedMethodId;
    }

    public FormattedMethodId getFormattedMethodId() {
        return formattedMethodId;
    }

    public CustList<ClassMethodId> getMethodIds() {
        return methodIds;
    }
}
