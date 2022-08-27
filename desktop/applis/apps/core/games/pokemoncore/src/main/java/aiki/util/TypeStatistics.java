package aiki.util;

import code.util.core.BoolVal;

public final class TypeStatistics extends CommonMap<TypeStatistic, BoolVal> {
    @Override
    protected BoolVal def() {
        return BoolVal.FALSE;
    }

    @Override
    protected boolean eq(TypeStatistic _one, TypeStatistic _two) {
        return _one.eq(_two);
    }
}
