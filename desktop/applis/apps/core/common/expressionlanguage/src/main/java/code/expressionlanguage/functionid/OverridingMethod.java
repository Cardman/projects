package code.expressionlanguage.functionid;

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
