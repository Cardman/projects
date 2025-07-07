package aiki.beans;

public final class IntBeanChgFighter6 {
    private final IntBeanChgList<String> movesToBeLearnt;
    private final IntBeanChgAction action;
    private final IntBeanChgBool successfulMove;
    private final IntBeanChgBool changed;

    public IntBeanChgFighter6(IntBeanChgList<String> _n, IntBeanChgAction _g, IntBeanChgBool _h, IntBeanChgBool _c) {
        this.movesToBeLearnt = _n;
        this.action = _g;
        this.successfulMove = _h;
        this.changed = _c;
    }

    public IntBeanChgList<String> getMovesToBeLearnt() {
        return movesToBeLearnt;
    }

    public IntBeanChgAction getAction() {
        return action;
    }

    public IntBeanChgBool getSuccessfulMove() {
        return successfulMove;
    }

    public IntBeanChgBool getChanged() {
        return changed;
    }
}
