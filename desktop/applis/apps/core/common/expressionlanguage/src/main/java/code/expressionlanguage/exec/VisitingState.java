package code.expressionlanguage.exec;

public enum VisitingState {
    NONE(false, false),
    INSTR(true, false),
    INSTR_EXPR(true, true),
    EXPR(false, true);
    private final boolean visited;
    private final boolean visitedExp;

    VisitingState(boolean _i, boolean _e) {
        this.visited = _i;
        this.visitedExp = _e;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean isVisitedExp() {
        return visitedExp;
    }
}
