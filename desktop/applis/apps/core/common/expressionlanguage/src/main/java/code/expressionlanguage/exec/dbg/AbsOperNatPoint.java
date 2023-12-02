package code.expressionlanguage.exec.dbg;

public interface AbsOperNatPoint {
    boolean isEnabled();
    BreakPointCondition result(int _mode);
}
