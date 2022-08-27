package aiki.util;

import code.maths.Rate;

public final class TeamPositionsRate extends TeamPositions<Rate> {

    @Override
    protected Rate def() {
        return Rate.zero();
    }
}
