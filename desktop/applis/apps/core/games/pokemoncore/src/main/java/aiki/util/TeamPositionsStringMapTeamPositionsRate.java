package aiki.util;

import code.util.StringMap;

public final class TeamPositionsStringMapTeamPositionsRate extends TeamPositions<StringMap<TeamPositionsRate>> {
    @Override
    protected StringMap<TeamPositionsRate> def() {
        return new StringMap<TeamPositionsRate>();
    }
}
