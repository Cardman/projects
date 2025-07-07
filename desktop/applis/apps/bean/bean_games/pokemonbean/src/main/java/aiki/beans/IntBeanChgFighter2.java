package aiki.beans;

public final class IntBeanChgFighter2 {
    private final IntBeanChgString lastUsedItem;
    private final IntBeanChgString item;
    private final IntBeanChgString currentAbility;
    private final IntBeanChgLgInt nbRounds;
    private final IntBeanChgList<String> types;
    private final IntBeanChgString ability;
    private final IntBeanChgRate remainingHp;

    public IntBeanChgFighter2(IntBeanChgString _n, IntBeanChgString _i, IntBeanChgString _g, IntBeanChgLgInt _w, IntBeanChgList<String> _h, IntBeanChgString _c, IntBeanChgRate _e) {
        this.lastUsedItem = _n;
        this.item = _i;
        this.currentAbility = _g;
        this.nbRounds = _w;
        this.types = _h;
        this.ability = _c;
        this.remainingHp = _e;
    }

    public IntBeanChgString getLastUsedItem() {
        return lastUsedItem;
    }

    public IntBeanChgString getItem() {
        return item;
    }

    public IntBeanChgString getCurrentAbility() {
        return currentAbility;
    }

    public IntBeanChgLgInt getNbRounds() {
        return nbRounds;
    }

    public IntBeanChgList<String> getTypes() {
        return types;
    }

    public IntBeanChgString getAbility() {
        return ability;
    }

    public IntBeanChgRate getRemainingHp() {
        return remainingHp;
    }
}
