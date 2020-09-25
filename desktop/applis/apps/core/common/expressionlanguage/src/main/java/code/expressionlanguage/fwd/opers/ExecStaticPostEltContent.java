package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class ExecStaticPostEltContent {
    private final ExecStaticEltContent staticEltContent;
    private final boolean post;
    public ExecStaticPostEltContent(ClassMethodId _cl, boolean _post) {
        staticEltContent = new ExecStaticEltContent(_cl);
        post = _post;
    }

    public boolean isPost() {
        return post;
    }

    public String getClassName() {
        return staticEltContent.getClassName();
    }

    public MethodAccessKind getKind() {
        return staticEltContent.getKind();
    }
}
