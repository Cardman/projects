package aiki.game.fight;
import aiki.DataBase;
import code.util.StringList;


public final class ChoiceOfEvolutionAndMoves {

    private String name = DataBase.EMPTY_STRING;

    private StringList keptMoves = new StringList();

    private String ability = DataBase.EMPTY_STRING;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public StringList getKeptMoves() {
        return keptMoves;
    }

    public void setKeptMoves(StringList _keptMoves) {
        keptMoves = _keptMoves;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String _ability) {
        ability = _ability;
    }
}
