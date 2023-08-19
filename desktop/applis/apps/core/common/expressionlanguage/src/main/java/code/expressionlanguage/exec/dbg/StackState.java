package code.expressionlanguage.exec.dbg;

public final class StackState {
    private boolean visitedState;
    private boolean checkingBp;

    public void resetVisitAndCheckBp() {
        visitedNone();
        setCheckingBp(true);
    }

    public void visitedNone() {
        this.visitedState = false;
    }

    public boolean visitedExp() {
        return visitedState;
    }

    public void visitExp() {
        visitedState = true;
    }

    public boolean isCheckingBp() {
        return checkingBp;
    }

    public void setCheckingBp(boolean _c) {
        this.checkingBp = _c;
    }

}
