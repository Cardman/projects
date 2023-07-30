package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.VisitingState;

public final class StackState {
    private VisitingState visitedState = VisitingState.NONE;
    private boolean checkingBp;
    private boolean mustStop;

    public void resetVisit(boolean _checkingBp) {
        this.visitedState = VisitingState.NONE;
        setCheckingBp(_checkingBp);
    }

    public boolean visitedInst() {
        return getVisitedState().isVisited();
    }

    public void visitInst() {
        if (visitedState == VisitingState.EXPR) {
            visitedState = VisitingState.INSTR_EXPR;
        } else {
            visitedState = VisitingState.INSTR;
        }
    }

    public boolean visitedExp() {
        return getVisitedState().isVisitedExp();
    }

    public void visitExp() {
        visitedState = VisitingState.EXPR;
    }

    public VisitingState getVisitedState() {
        return visitedState;
    }

    public boolean isCheckingBp() {
        return checkingBp;
    }

    public void setCheckingBp(boolean _c) {
        this.checkingBp = _c;
    }

    public boolean isMustStop() {
        return mustStop;
    }

    public void setMustStop(boolean _m) {
        this.mustStop = _m;
    }
}
