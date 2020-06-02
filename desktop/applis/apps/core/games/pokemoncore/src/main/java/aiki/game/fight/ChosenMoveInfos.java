package aiki.game.fight;
import aiki.game.UsesOfMove;
import code.util.StringList;

public class ChosenMoveInfos {

    private UsesOfMove uses;

    private StringList types;

    private String name;

    private boolean usable;

    public UsesOfMove getUses() {
        return uses;
    }

    public void setUses(UsesOfMove _uses) {
        uses = _uses;
    }

    public StringList getTypes() {
        return types;
    }

    public void setTypes(StringList _types) {
        types = _types;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean _usable) {
        usable = _usable;
    }
}
