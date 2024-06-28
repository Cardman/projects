package code.expressionlanguage.adv;

public final class OpenExpDebGuiImplEvent extends AbsOpenDebGuiImplEvent {

    public OpenExpDebGuiImplEvent(WindowWithTreeImpl _e, AbsDebuggerGui _d) {
        super(_e, _d);
    }

    @Override
    protected AbsAnalyzingDebugExpEvent event() {
        return new ExpAnalyzingDebugExpEvent(getDbg().getMenuManage(), getExpressionEditor(), getDbg());
    }
}
