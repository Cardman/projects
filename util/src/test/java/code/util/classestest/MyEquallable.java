package code.util.classestest;

import code.util.ints.Equallable;

public final class MyEquallable implements Equallable<MyEquallable> {
    private int info;

    public MyEquallable(int _info) {
        info = _info;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int _info) {
        info = _info;
    }

    @Override
    public boolean eq(MyEquallable _g) {
        return info == _g.info;
    }
}
