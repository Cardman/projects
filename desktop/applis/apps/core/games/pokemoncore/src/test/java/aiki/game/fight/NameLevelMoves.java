package aiki.game.fight;
import aiki.fight.pokemon.NameLevel;
import code.util.StringList;

public final class NameLevelMoves {

    private NameLevel nameLevel;

    private StringList moves;

    public NameLevelMoves(NameLevel _nameLevel, StringList _moves) {
        nameLevel = _nameLevel;
        moves = _moves;
    }

    public NameLevel getNameLevel() {
        return nameLevel;
    }

    public StringList getMoves() {
        return moves;
    }
}
