package code.expressionlanguage.classes;

import code.util.ints.Cmp;

public class StrangeCmp implements Cmp<Integer> {

    @Override
    public boolean eq(Integer _g) {
        return false;
    }

    @Override
    public int cmp(Integer _other) {
        return 0;
    }

}
