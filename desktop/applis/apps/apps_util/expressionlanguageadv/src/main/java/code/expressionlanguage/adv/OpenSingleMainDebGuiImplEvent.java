package code.expressionlanguage.adv;

public final class OpenSingleMainDebGuiImplEvent extends AbsOpenDebGuiImplEvent {

    public OpenSingleMainDebGuiImplEvent(WindowWithTreeImpl _e, AbsDebuggerGui _d) {
        super(_e, _d);
    }

    @Override
    protected AbsAnalyzingDebugExpEvent event() {
        return new InitAnalyzingDebugExpEvent(getDbg().getMenuManage(), getExpressionEditor(), getDbg());
    }
}
