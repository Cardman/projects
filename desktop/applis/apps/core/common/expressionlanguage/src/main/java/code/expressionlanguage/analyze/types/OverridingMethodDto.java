package code.expressionlanguage.analyze.types;

import code.expressionlanguage.functionid.FormattedMethodId;
import code.util.CustList;

public final class OverridingMethodDto {
    private final FormattedMethodId formattedMethodId;

    private CustList<GeneStringOverridable> methodIds = new CustList<GeneStringOverridable>();

    public OverridingMethodDto(FormattedMethodId formattedMethodId) {
        this.formattedMethodId = formattedMethodId;
    }

    public FormattedMethodId getFormattedMethodId() {
        return formattedMethodId;
    }

    public CustList<GeneStringOverridable> getMethodIds() {
        return methodIds;
    }
}
