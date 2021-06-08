package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.StringExpUtil;

public final class ExecArrayInstancingContent {
    private final String methodName;

    private final String className;

    public ExecArrayInstancingContent(AnaArrayInstancingContent _cont) {
        this.methodName = _cont.getMethodName();
        this.className = _cont.getClassName();
    }

    public ExecArrayInstancingContent(String _cl) {
        this.methodName = "";
        this.className = StringExpUtil.getQuickComponentType(_cl);
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }
}
