package aiki.game.fight.util;
import code.util.EqList;
import aiki.game.fight.TeamPosition;

public final class NextUsers {

    private final EqList<TeamPosition> nextFighters;

    private final EqList<TeamPosition> itemUsers;

    public NextUsers(EqList<TeamPosition> _nextFighters, EqList<TeamPosition> _itemUsers) {
        nextFighters = _nextFighters;
        itemUsers = _itemUsers;
    }

    public EqList<TeamPosition> getNextFighters() {
        return nextFighters;
    }

    public EqList<TeamPosition> getItemUsers() {
        return itemUsers;
    }
}
