package aiki.game.fight;

import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import code.util.StringList;

public final class MoveDataTypesEffect {

    private final MoveData att;
    private final int index;
    private final StringList types;
    public MoveDataTypesEffect(Fight _fight, TeamPosition _thrower, String _move, DataBase _import) {
        att = _import.getMove(_move);
        index = att.indexOfPrimaryEffect();
        types = FightMoves.moveTypes(_fight,_thrower, _move, _import);
    }

    public int getIndex() {
        return index;
    }

    public StringList getTypes() {
        return types;
    }

    public MoveData getAtt() {
        return att;
    }
}
