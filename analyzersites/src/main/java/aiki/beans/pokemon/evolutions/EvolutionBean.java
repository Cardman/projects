package aiki.beans.pokemon.evolutions;
import code.bean.Accessible;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;

public class EvolutionBean extends CommonBean {

    @Accessible
    private String displayBase;

    @Accessible
    private String base;

    private Evolution evo;

    @Accessible
    private String displayName;

    @Accessible
    private String name;

    @Accessible
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

    @Accessible
    private String clickEvo(Long _index) {
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
}
