package code.util.classestest;

import code.util.Numbers;
import code.util.ints.Cmp;

public final class MyCmp implements Cmp<MyCmp> {
    private int info;

    public MyCmp(int _info) {
        info = _info;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int _info) {
        info = _info;
    }

    @Override
    public boolean eq(MyCmp _g) {
        return info == _g.info;
    }

    @Override
    public int cmp(MyCmp _other) {
        return Numbers.compareLg(info,_other.info);
    }

    public String display() {
        return Integer.toString(info);
    }
}
