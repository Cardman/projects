package code.expressionlanguage.classes;

import code.util.ints.Cmp;

public class MyCmpClass implements Cmp<MyCmpClass> {

    @Override
    public boolean eq(MyCmpClass _g) {
        return false;
    }

    @Override
    public int cmp(MyCmpClass _other) {
        if (eq(_other)) {
            return 0;
        }
        return -1;
    }

}
