package aiki.beans.facade.dto;
import code.bean.Accessible;
import code.util.StringList;

public final class PokemonLine {

    @Accessible
    private String displayName;

    @Accessible
    private String name;

    @Accessible
    private StringList types;

    @Accessible
    private int evolutions;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String _displayName) {
        displayName = _displayName;
    }
    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public StringList getTypes() {
        return types;
    }

    public void setTypes(StringList _types) {
        types = _types;
    }

    public int getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(int _evolutions) {
        evolutions = _evolutions;
    }
}
