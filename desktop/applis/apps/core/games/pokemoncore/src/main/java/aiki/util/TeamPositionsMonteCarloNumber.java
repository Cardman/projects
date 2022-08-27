package aiki.util;

import code.maths.montecarlo.MonteCarloNumber;

public final class TeamPositionsMonteCarloNumber extends TeamPositions<MonteCarloNumber> {
    @Override
    protected MonteCarloNumber def() {
        return new MonteCarloNumber();
    }
}
