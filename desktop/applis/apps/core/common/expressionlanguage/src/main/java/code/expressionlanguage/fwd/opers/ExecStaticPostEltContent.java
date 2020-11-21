package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class ExecStaticPostEltContent {
    private final ExecStaticEltContent staticEltContent;
    private final boolean post;

    public ExecStaticPostEltContent(AnaTypeFct _pair, String _className, boolean _post) {
        staticEltContent = new ExecStaticEltContent(_pair,_className);
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
