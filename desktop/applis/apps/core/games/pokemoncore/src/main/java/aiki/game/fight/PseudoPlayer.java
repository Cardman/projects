package aiki.game.fight;
import aiki.fight.pokemon.NameLevel;
import aiki.map.pokemon.PokemonPlayer;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;

public class PseudoPlayer {

    private CustList<PseudoPokemonPlayer> team;

    private CustList<StringList> items;

    private CustList<CustList<StringList>> usedStones;

    private CustList<CustList<NameLevel>> evolutions;

    public PseudoPlayer(CustList<PokemonPlayer> _team,
            CustList<CustList<NameLevel>> _evolutions) {
        team = new CustList<PseudoPokemonPlayer>();
        for (PokemonPlayer p: _team) {
            team.add(new PseudoPokemonPlayer(p));
        }
        items = new CustList<StringList>();
        usedStones = new CustList<CustList<StringList>>();
        evolutions = new CustList<CustList<NameLevel>>();
        for (CustList<NameLevel> l: _evolutions) {
            CustList<NameLevel> copy_;
            copy_ = new CustList<NameLevel>();
            for (NameLevel p: l) {
                copy_.add(new NameLevel(p));
            }
            evolutions.add(copy_);
        }
    }

    public CustList<PseudoPokemonPlayer> getTeam() {
        return team;
    }

    public CustList<StringList> getItems() {
        return items;
    }

    public void setItems(CustList<StringList> _items) {
        items = _items;
    }

    public CustList<CustList<StringList>> getUsedStones() {
        return usedStones;
    }

    public CustList<CustList<NameLevel>> getEvolutions() {
        return evolutions;
    }
}
