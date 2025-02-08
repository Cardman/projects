package aiki.beans.facade.dto;
import aiki.beans.TranslatedKey;
import code.util.StringList;

public final class PokemonLine {
    private String displayName;
    private TranslatedKey key;
    private StringList types;
    private int evolutions;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String _displayName) {
        displayName = _displayName;
    }
    public String getName() {
        return getKey().getKey();
    }

    public TranslatedKey getKey() {
        return key;
    }

    public void setKey(TranslatedKey _k) {
        this.key = _k;
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