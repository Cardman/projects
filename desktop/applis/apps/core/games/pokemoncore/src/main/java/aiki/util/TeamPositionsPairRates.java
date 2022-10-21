package aiki.util;

import code.maths.Rate;

public final class TeamPositionsPairRates extends TeamPositions<PairRates> {

    @Override
    protected PairRates def() {
        return new PairRates(Rate.zero(),Rate.zero());
    }
}
