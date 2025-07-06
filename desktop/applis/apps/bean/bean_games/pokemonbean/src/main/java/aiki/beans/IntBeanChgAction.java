package aiki.beans;

public final class IntBeanChgAction {
    private final IntBeanChgKindAction kindAction;
    private final IntBeanChgString first;
    private final IntBeanChgString last;
    private final IntBeanChgString item;
    private final IntBeanChgInt sub;
    private final IntBeanChgTargetCoords target;
    private final IntBeanChgBool team;

    public IntBeanChgAction(IntBeanChgKindAction _k, IntBeanChgString _f, IntBeanChgString _l, IntBeanChgString _i, IntBeanChgInt _s, IntBeanChgTargetCoords _a, IntBeanChgBool _e) {
        this.kindAction = _k;
        this.first = _f;
        this.last = _l;
        this.item = _i;
        this.sub = _s;
        this.target = _a;
        this.team = _e;
    }

    public IntBeanChgKindAction getKindAction() {
        return kindAction;
    }

    public IntBeanChgString getFirst() {
        return first;
    }

    public IntBeanChgString getLast() {
        return last;
    }

    public IntBeanChgString getItem() {
        return item;
    }

    public IntBeanChgInt getSub() {
        return sub;
    }

    public IntBeanChgTargetCoords getTarget() {
        return target;
    }

    public IntBeanChgBool getTeam() {
        return team;
    }
}
