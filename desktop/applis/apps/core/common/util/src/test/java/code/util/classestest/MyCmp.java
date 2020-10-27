package code.util.classestest;

import code.util.core.NumberUtil;
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
        return NumberUtil.compareLg(info,_other.info);
    }

    public String display() {
        return Long.toString(info);
    }
}
