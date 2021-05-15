package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.Forwards;

public final class ExecStaticPostEltContent {
    private final ExecStaticEltContent staticEltContent;
    private final boolean post;

    public ExecStaticPostEltContent(ClassMethodIdMemberIdTypeFct _id, boolean _post, Forwards _fwd) {
        staticEltContent = new ExecStaticEltContent(_id,_fwd);
        post = _post;
    }

    public boolean isPost() {
        return post;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return staticEltContent.getFormattedType();
    }

    public MethodAccessKind getKind() {
        return staticEltContent.getKind();
    }
}
