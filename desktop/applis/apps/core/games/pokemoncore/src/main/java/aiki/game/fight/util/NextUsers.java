package aiki.game.fight.util;
import aiki.util.TeamPositionList;

public final class NextUsers {

    private final TeamPositionList nextFighters;

    private final TeamPositionList itemUsers;

    public NextUsers(TeamPositionList _nextFighters, TeamPositionList _itemUsers) {
        nextFighters = _nextFighters;
        itemUsers = _itemUsers;
    }

    public TeamPositionList getNextFighters() {
        return nextFighters;
    }

    public TeamPositionList getItemUsers() {
        return itemUsers;
    }
}
