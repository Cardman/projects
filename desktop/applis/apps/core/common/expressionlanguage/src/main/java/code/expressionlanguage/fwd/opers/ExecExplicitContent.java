package code.expressionlanguage.fwd.opers;

import code.util.core.StringUtil;

public final class ExecExplicitContent {
    private final String className;
    private final String classNameOwner;
    private final int offset;
    public ExecExplicitContent(AnaExplicitContent _cont) {
        className = _cont.getClassName();
        classNameOwner = _cont.getClassNameOwner();
        offset = _cont.getOffset();
    }
    public ExecExplicitContent(AnaCallFctContent _cont) {
        className = _cont.getClassMethodId().getClassName();
        classNameOwner = _cont.getClassMethodId().getClassName();
        offset = StringUtil.getFirstPrintableCharIndex(_cont.getMethodName());
    }

    public String getClassNameOwner() {
        return classNameOwner;
    }

    public int getOffset() {
        return offset;
    }

    public String getClassName() {
        return className;
    }
}
