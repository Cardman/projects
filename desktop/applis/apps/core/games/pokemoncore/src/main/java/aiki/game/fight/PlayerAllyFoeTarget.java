package aiki.game.fight;

import aiki.util.TargetCoordsList;

class PlayerAllyFoeTarget {
    private final TeamPosition userPk;
    private final TeamPosition partner;
    private final TargetCoordsList koFoeFighters;
    private final TargetCoordsList notKoFoeFighters;

    PlayerAllyFoeTarget(TeamPosition _u, TeamPosition _p, TargetCoordsList _ko, TargetCoordsList _notKo) {
        this.userPk = _u;
        this.partner = _p;
        this.koFoeFighters = _ko;
        this.notKoFoeFighters = _notKo;
    }

    public TargetCoordsList getKoFoeFighters() {
        return koFoeFighters;
    }

    public TargetCoordsList getNotKoFoeFighters() {
        return notKoFoeFighters;
    }

    public TeamPosition getPartner() {
        return partner;
    }

    public TeamPosition getUserPk() {
        return userPk;
    }
}
