package aiki;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;


public final class ExchangedData {

    private StringList abilities;
    private StringList items;
    private StringMap<GenderRepartition> genderRepartitions;
    private PokemonPlayer pokemon;
    private int indexTeam;

    public ExchangedData(){}

    public ExchangedData(DataBase _dataBase) {
        genderRepartitions = new StringMap<GenderRepartition>();
        for (String p: _dataBase.getPokedex().getKeys()) {
            genderRepartitions.put(p, _dataBase.getPokedex().getVal(p).getGenderRep());
        }
        abilities = new StringList(_dataBase.getAbilities().getKeys());
        items = new StringList(_dataBase.getItems().getKeys());
    }

    public NatTreeMap<Byte,PokemonPlayer> getTeam(CustList<UsablePokemon> _otherTeam) {
        NatTreeMap<Byte,PokemonPlayer> team_ = new NatTreeMap<Byte,PokemonPlayer>();
        byte i_ = CustList.FIRST_INDEX;
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
        if (!getAbilities().containsObj(pokemon.getAbility())) {
            pokemon = null;
            return;
        }
        if (!pokemon.getItem().isEmpty()) {
            if (!getItems().containsObj(pokemon.getItem())) {
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
