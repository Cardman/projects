package aiki.game.fight.util;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public final class MovesAbilities {

    private StringList moves;

    private StringList abilities;

    public MovesAbilities() {
    }

    public MovesAbilities(StringList _moves, StringList _abilities) {
        moves = _moves;
        abilities = _abilities;
    }

    public StringList getMoves() {
        return moves;
    }

    public void setMoves(StringList _moves) {
        moves = _moves;
    }

    public StringList getAbilities() {
        return abilities;
    }

    public void setAbilities(StringList _abilities) {
        abilities = _abilities;
    }
}
