package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.StringExpUtil;

public final class ExecArrayInstancingContent {
    private final int methodName;

    private final String className;

    public ExecArrayInstancingContent(AnaArrayInstancingContent _cont) {
        this.methodName = StringExpUtil.getOffset(_cont.getMethodName());
        this.className = _cont.getClassName();
    }

    public int getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }
}
