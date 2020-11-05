package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecStaticEltContent {
    private final MethodAccessKind kind;
    private final String className;
    public ExecStaticEltContent(ClassMethodId _cl) {
        kind = FetchMemberUtil.getKind(_cl);
        className = FetchMemberUtil.getType(_cl);
    }

    public String getClassName() {
        return className;
    }

    public MethodAccessKind getKind() {
        return kind;
    }
}
