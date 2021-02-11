package code.expressionlanguage.options;

public final class WarningShow {
    private boolean ternary;
    private boolean unusedParameterStaticMethod;

    public static boolean isTernary(WarningShow _warningShow) {
        if (_warningShow == null) {
            return false;
        }
        return _warningShow.isTernary();
    }

    public static boolean isUnusedParameterStaticMethod(WarningShow _warningShow) {
        if (_warningShow == null) {
            return false;
        }
        return _warningShow.isUnusedParameterStaticMethod();
    }
    public boolean isTernary() {
        return ternary;
    }

    public void setTernary(boolean _ternary) {
        this.ternary = _ternary;
    }

    public boolean isUnusedParameterStaticMethod() {
        return unusedParameterStaticMethod;
    }

    public void setUnusedParameterStaticMethod(boolean _unusedParameterStaticMethod) {
        this.unusedParameterStaticMethod = _unusedParameterStaticMethod;
    }
}
