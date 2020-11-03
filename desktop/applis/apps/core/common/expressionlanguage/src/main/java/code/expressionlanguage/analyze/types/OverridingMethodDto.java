package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.util.CustList;

public final class OverridingMethodDto {
    private final FormattedMethodId formattedMethodId;

    private final CustList<GeneStringOverridable> methodIds = new CustList<GeneStringOverridable>();

    public OverridingMethodDto(FormattedMethodId _formattedMethodId) {
        this.formattedMethodId = _formattedMethodId;
    }

    public FormattedMethodId getFormattedMethodId() {
        return formattedMethodId;
    }

    public CustList<GeneStringOverridable> getMethodIds() {
        return methodIds;
    }
}
