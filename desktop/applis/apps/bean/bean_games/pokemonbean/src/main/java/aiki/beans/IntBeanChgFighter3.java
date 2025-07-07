package aiki.beans;

public final class IntBeanChgFighter3 {
    private final IntBeanChgBool acted;
    private final IntBeanChgInt groundPlace;
    private final IntBeanChgInt groundPlaceSubst;
    private final IntBeanChgRate wonExp;
    private final IntBeanChgList<String> protectedAgainstMoveTypes;
    private final IntBeanChgRate wonExpSinceLastLevel;
    private final IntBeanChgRate clone;

    public IntBeanChgFighter3(IntBeanChgBool _n, IntBeanChgInt _i, IntBeanChgInt _g, IntBeanChgRate _w, IntBeanChgList<String> _h, IntBeanChgRate _c, IntBeanChgRate _e) {
        this.acted = _n;
        this.groundPlace = _i;
        this.groundPlaceSubst = _g;
        this.wonExp = _w;
        this.protectedAgainstMoveTypes = _h;
        this.wonExpSinceLastLevel = _c;
        this.clone = _e;
    }

    public IntBeanChgBool getActed() {
        return acted;
    }

    public IntBeanChgInt getGroundPlace() {
        return groundPlace;
    }

    public IntBeanChgInt getGroundPlaceSubst() {
        return groundPlaceSubst;
    }

    public IntBeanChgRate getWonExp() {
        return wonExp;
    }

    public IntBeanChgList<String> getProtectedAgainstMoveTypes() {
        return protectedAgainstMoveTypes;
    }

    public IntBeanChgRate getWonExpSinceLastLevel() {
        return wonExpSinceLastLevel;
    }

    public IntBeanChgRate getClone() {
        return clone;
    }
}
