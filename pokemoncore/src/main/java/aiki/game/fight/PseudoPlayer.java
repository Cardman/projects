package aiki.game.fight;
import aiki.fight.pokemon.NameLevel;
import aiki.map.pokemon.PokemonPlayer;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;

public class PseudoPlayer {

    private CustList<PseudoPokemonPlayer> team;

    private EqList<StringList> items;

    private CustList<EqList<StringList>> usedStones;

    private CustList<EqList<NameLevel>> evolutions;

    public PseudoPlayer(CustList<PokemonPlayer> _team,
            CustList<EqList<NameLevel>> _evolutions) {
        team = new CustList<PseudoPokemonPlayer>();
        for (PokemonPlayer p: _team) {
            team.add(new PseudoPokemonPlayer(p));
        }
        items = new EqList<StringList>();
        usedStones = new CustList<EqList<StringList>>();
        evolutions = new CustList<EqList<NameLevel>>();
        for (EqList<NameLevel> l: _evolutions) {
            EqList<NameLevel> copy_;
            copy_ = new EqList<NameLevel>();
            for (NameLevel p: l) {
                copy_.add(new NameLevel(p));
            }
            evolutions.add(copy_);
        }
    }

    public CustList<PseudoPokemonPlayer> getTeam() {
        return team;
    }

    public EqList<StringList> getItems() {
        return items;
    }

    public void setItems(EqList<StringList> _items) {
        items = _items;
    }

    public CustList<EqList<StringList>> getUsedStones() {
        return usedStones;
    }

    public CustList<EqList<NameLevel>> getEvolutions() {
        return evolutions;
    }
}
