package aiki.beans;

public final class IntBeanChgFighter1 {
    private final IntBeanChgString name;
    private final IntBeanChgString nickname;
    private final IntBeanChgGender gender;
    private final IntBeanChgRate weight;
    private final IntBeanChgRate height;
    private final IntBeanChgString currentName;
    private final IntBeanChgGender currentGender;

    public IntBeanChgFighter1(IntBeanChgString _n, IntBeanChgString _i, IntBeanChgGender _g, IntBeanChgRate _w, IntBeanChgRate _h, IntBeanChgString _c, IntBeanChgGender _e) {
        this.name = _n;
        this.nickname = _i;
        this.gender = _g;
        this.weight = _w;
        this.height = _h;
        this.currentName = _c;
        this.currentGender = _e;
    }

    public IntBeanChgString getName() {
        return name;
    }

    public IntBeanChgString getNickname() {
        return nickname;
    }

    public IntBeanChgGender getGender() {
        return gender;
    }

    public IntBeanChgRate getWeight() {
        return weight;
    }

    public IntBeanChgRate getHeight() {
        return height;
    }

    public IntBeanChgString getCurrentName() {
        return currentName;
    }

    public IntBeanChgGender getCurrentGender() {
        return currentGender;
    }
}
