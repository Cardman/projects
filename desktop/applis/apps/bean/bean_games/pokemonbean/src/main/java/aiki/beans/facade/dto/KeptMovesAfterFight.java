package aiki.beans.facade.dto;
import code.util.StringList;

public final class KeptMovesAfterFight {

    private StringList moves;

    private StringList evolutions;

    private String ability;

    public StringList getMoves() {
        return moves;
    }

    public void setMoves(StringList _moves) {
        moves = _moves;
    }

    public StringList getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(StringList _evolutions) {
        evolutions = _evolutions;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String _ability) {
        ability = _ability;
    }
}