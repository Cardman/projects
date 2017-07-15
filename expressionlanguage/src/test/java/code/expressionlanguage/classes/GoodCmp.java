package code.expressionlanguage.classes;

import code.util.ints.Cmp;

public class GoodCmp implements Cmp<GoodCmp> {

    @Override
    public boolean eq(GoodCmp _g) {
        return false;
    }

    @Override
    public int cmp(GoodCmp _other) {
        return 0;
    }

}
