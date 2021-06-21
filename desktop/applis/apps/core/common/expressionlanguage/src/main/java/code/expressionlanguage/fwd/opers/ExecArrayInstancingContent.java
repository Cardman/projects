package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.StringExpUtil;

public final class ExecArrayInstancingContent {
    private final int methodName;

    private final String className;

    public ExecArrayInstancingContent(AnaArrayInstancingContent _cont) {
        this.methodName = StringExpUtil.getOffset(_cont.getMethodName());
        this.className = _cont.getClassName();
    }

    public ExecArrayInstancingContent(String _cl) {
        this.methodName = 0;
        this.className = StringExpUtil.getQuickComponentType(_cl);
    }

    public int getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }
}
