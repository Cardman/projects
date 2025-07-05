package aiki.util;

import code.maths.Rate;

public final class TargetCoordssRate extends TargetCoordssParam<Rate> {
    @Override
    protected Rate def() {
        return Rate.zero();
    }
}
