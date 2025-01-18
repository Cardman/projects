package aiki.game.fight;

import code.maths.Rate;
import code.util.Ints;

public final class PointFoeExpObject {
    private final Ints members;
    private final Ints porteursMultExp;
    private final Rate points;
    private final TeamPosition foe;

    public PointFoeExpObject(Ints _membres, Ints _porteurs, Rate _pts, int _adv) {
        this.members = _membres;
        this.porteursMultExp = _porteurs;
        this.points = _pts;
        this.foe = Fight.toFoeFighter(_adv);
    }

    public Ints getMembers() {
        return members;
    }

    public Ints getPorteursMultExp() {
        return porteursMultExp;
    }

    public Rate getPoints() {
        return points;
    }

    public TeamPosition getFoe() {
        return foe;
    }
}
