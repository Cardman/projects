package aiki.game.fight;

import code.maths.Rate;
import code.util.Bytes;

public final class PointFoeExpObject {
    private final Bytes members;
    private final Bytes porteursMultExp;
    private final Rate points;
    private final TeamPosition foe;

    public PointFoeExpObject(Bytes _membres, Bytes _porteurs, Rate _pts, byte _adv) {
        this.members = _membres;
        this.porteursMultExp = _porteurs;
        this.points = _pts;
        this.foe = Fight.toFoeFighter(_adv);
    }

    public Bytes getMembers() {
        return members;
    }

    public Bytes getPorteursMultExp() {
        return porteursMultExp;
    }

    public Rate getPoints() {
        return points;
    }

    public TeamPosition getFoe() {
        return foe;
    }
}
