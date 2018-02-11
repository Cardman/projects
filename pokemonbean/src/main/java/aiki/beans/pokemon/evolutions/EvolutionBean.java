package aiki.beans.pokemon.evolutions;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;
import code.util.StringList;
import code.util.StringMap;

public class EvolutionBean extends CommonBean {
    private String displayBase;
    private String base;

    private Evolution evo;
    private String displayName;
    private String name;
    private long index;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evo = data_.getPokemon(base).getEvolutions().getVal(name);
        displayBase = translationsPokemon_.getVal(base);
        displayName = translationsPokemon_.getVal(name);
    }

    protected Evolution getEvo() {
        return evo;
    }
    public String clickEvo(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(base);
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evolutions_.sortElts(new ComparatorTrStrings(translationsPokemon_));
        getForms().put(PK,evolutions_.get(_index.intValue()));
        return POKEMON;
    }

    protected String getBase() {
        return base;
    }

    public void setIndex(long _index) {
        index = _index;
    }

    public long getIndex() {
        return index;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setBase(String _base) {
        base = _base;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayBase() {
        return displayBase;
    }
}