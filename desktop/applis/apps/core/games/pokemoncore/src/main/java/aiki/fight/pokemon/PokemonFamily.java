package aiki.fight.pokemon;
import aiki.db.DataBase;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class PokemonFamily {

    private final CustList<StringList> stages;

    public PokemonFamily(DataBase _data, String _pokemonBase) {
        this(stages(_data, _pokemonBase));
    }
    public PokemonFamily(CustList<StringList> _st) {
        stages = _st;
    }

    private static CustList<StringList> stages(DataBase _data, String _pokemonBase) {
        CustList<StringList> stages_ = new CustList<StringList>();
        stages_.add(new StringList(_pokemonBase));
        StringList evolutionsLevels_ = new StringList();
        StringList currentEvolutions_ = new StringList();
        currentEvolutions_.add(_pokemonBase);
        evolutionsLevels_.add(_pokemonBase);
        while (true) {
            StringList newEvolutions_ = new StringList();
            for (String e: currentEvolutions_) {
                PokemonData fPk_ = _data.getPokemon(e);
                if (fPk_ == null) {
                    continue;
                }
                if (exitBuild(_data, _pokemonBase, evolutionsLevels_, newEvolutions_, fPk_)) {
                    return stages_;
                }
            }
            if (newEvolutions_.isEmpty()) {
                break;
            }
            stages_.add(newEvolutions_);
            evolutionsLevels_.addAllElts(newEvolutions_);
            currentEvolutions_ = new StringList(newEvolutions_);
        }
        return stages_;
    }

    private static boolean exitBuild(DataBase _data, String _pokemonBase, StringList _evolutionsLevels, StringList _newEvolutions, PokemonData _fPk) {
        for (String e_: _fPk.getEvolutions().getKeys()) {
            PokemonData evo_ = _data.getPokemon(e_);
            if (evo_ == null) {
                continue;
            }
            if (exitBuildTree(_data, _pokemonBase, _evolutionsLevels, _newEvolutions, e_, evo_)) {
                return true;
            }
            _newEvolutions.add(e_);
        }
        return false;
    }

    private static boolean exitBuildTree(DataBase _data, String _pokemonBase, StringList _evolutionsLevels, StringList _newEvolutions, String _e, PokemonData _evo) {
        if (!StringUtil.quickEq(_evo.getBaseEvo(), _pokemonBase)) {
            _data.setError(true);
        }
        if (StringUtil.contains(_evolutionsLevels, _e) || StringUtil.contains(_newEvolutions, _e)) {
            _data.setError(true);
            return true;
        }
        return false;
    }

    public CustList<StringList> getStages() {
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
