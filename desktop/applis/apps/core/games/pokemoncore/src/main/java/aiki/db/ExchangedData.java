package aiki.db;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;


public final class ExchangedData {

    private StringList abilities;
    private StringList items;
    private StringMap<GenderRepartition> genderRepartitions;
    private PokemonPlayer pokemon;
    private int indexTeam;

    public ExchangedData(){}

    public ExchangedData(DataBase _dataBase) {
        setGenderRepartitions(new StringMap<GenderRepartition>());
        for (String p: _dataBase.getPokedex().getKeys()) {
            genderRepartitions.put(p, _dataBase.getPokedex().getVal(p).getGenderRep());
        }
        setAbilities(new StringList(_dataBase.getAbilities().getKeys()));
        setItems(new StringList(_dataBase.getItems().getKeys()));
    }

    public ByteTreeMap<PokemonPlayer> getTeam(CustList<UsablePokemon> _otherTeam) {
        ByteTreeMap<PokemonPlayer> team_ = new ByteTreeMap<PokemonPlayer>();
        byte i_ = IndexConstants.FIRST_INDEX;
        i_--;
        for (UsablePokemon u: _otherTeam) {
            i_++;
            setPokemon((PokemonPlayer) u);
            check();
            if (getPokemon() != null) {
                team_.put(i_, (PokemonPlayer) u);
            }
        }
        return team_;
    }

    public void check() {
        if (!genderRepartitions.contains(pokemon.getName())) {
            pokemon = null;
            return;
        }
        GenderRepartition g_ = genderRepartitions.getVal(pokemon.getName());
        if (!g_.getPossibleGenders().containsObj(pokemon.getGender())) {
            pokemon = null;
            return;
        }
        if (!StringUtil.contains(getAbilities(), pokemon.getAbility())) {
            pokemon = null;
            return;
        }
        if (!pokemon.getItem().isEmpty()) {
            if (!StringUtil.contains(getItems(), pokemon.getItem())) {
                pokemon = null;
                return;
            }
        }
    }

    public StringList getAbilities() {
        return abilities;
    }

    public void setAbilities(StringList _abilities) {
        abilities = _abilities;
    }

    public StringList getItems() {
        return items;
    }

    public void setItems(StringList _items) {
        items = _items;
    }

    public StringMap<GenderRepartition> getGenderRepartitions() {
        return genderRepartitions;
    }

    public void setGenderRepartitions(
            StringMap<GenderRepartition> _genderRepartitions) {
        genderRepartitions = _genderRepartitions;
    }

    public PokemonPlayer getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonPlayer _pokemon) {
        pokemon = _pokemon;
    }

    public int getIndexTeam() {
        return indexTeam;
    }

    public void setIndexTeam(int _indexTeam) {
        indexTeam = _indexTeam;
    }

}
