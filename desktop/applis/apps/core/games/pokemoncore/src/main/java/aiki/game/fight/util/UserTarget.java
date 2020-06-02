package aiki.game.fight.util;
import aiki.game.fight.TeamPosition;

public final class UserTarget {

    private final TeamPosition user;

    private final TeamPosition target;

    public UserTarget(TeamPosition _user, TeamPosition _target) {
        user = _user;
        target = _target;
    }

    public TeamPosition getUser() {
        return user;
    }

    public TeamPosition getTarget() {
        return target;
    }
}
