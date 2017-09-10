package aiki.fight.pokemon;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.util.EqList;
import code.util.StringList;

public final class PokemonFamily {

    private final EqList<StringList> stages = new EqList<StringList>();

    public PokemonFamily(DataBase _data, String _pokemonBase) {
        stages.add(new StringList(_pokemonBase));
        StringList evolutionsLevels_ = new StringList();
        StringList currentEvolutions_ = new StringList();
        currentEvolutions_.add(_pokemonBase);
        evolutionsLevels_.add(_pokemonBase);
        StringList newEvolutions_ = new StringList();
        while (true) {
            newEvolutions_ = new StringList();
            for (String e: currentEvolutions_) {
                PokemonData fPk_ = _data.getPokemon(e);
                for (String e_: fPk_.getEvolutions().getKeys()) {
                    PokemonData evo_ = _data.getPokemon(e_);
                    if (!StringList.quickEq(evo_.getBaseEvo(), _pokemonBase)) {
                        throw new DataException();
                    }
                    if (evolutionsLevels_.containsObj(e_)) {
                        throw new DataException();
                    }
                    if (newEvolutions_.containsObj(e_)) {
                        throw new DataException();
                    }
                    newEvolutions_.add(e_);
                }
            }
            if (newEvolutions_.isEmpty()) {
                break;
            }
            stages.add(newEvolutions_);
            evolutionsLevels_.addAllElts(newEvolutions_);
            currentEvolutions_ = new StringList(newEvolutions_);
        }
    }

    public EqList<StringList> getStages() {
        return stages;
    }

    public StringList getAllPokemon() {
        StringList list_ = new StringList();
        for (StringList s: stages) {
            list_.addAllElts(s);
        }
        return list_;
    }
}
