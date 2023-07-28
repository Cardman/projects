package code.expressionlanguage.exec;

public enum VisitingState {
    NONE(false, false),
    INSTR(true, false),
    INSTR_EXPR(true, true),
    EXPR(false, true);
    private final boolean visited;
    private final boolean visitedExp;

    VisitingState(boolean visited, boolean visitedExp) {
        this.visited = visited;
        this.visitedExp = visitedExp;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean isVisitedExp() {
        return visitedExp;
    }
}
