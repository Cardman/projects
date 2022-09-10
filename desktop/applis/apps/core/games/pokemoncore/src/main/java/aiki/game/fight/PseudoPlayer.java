package aiki.game.fight;
import aiki.fight.pokemon.NameLevel;
import aiki.map.pokemon.PokemonPlayer;
import code.util.CustList;
import code.util.StringList;

public class PseudoPlayer {

    private final CustList<PseudoPokemonPlayer> team;

    private final CustList<StringList> items;

    private final CustList<CustList<StringList>> usedStones;

    private final CustList<CustList<NameLevel>> evolutions;

    public PseudoPlayer(CustList<PokemonPlayer> _team,
                        CustList<CustList<NameLevel>> _evolutions) {
        this(_team,_evolutions,new CustList<StringList>());
    }
    public PseudoPlayer(CustList<PokemonPlayer> _team,
            CustList<CustList<NameLevel>> _evolutions, CustList<StringList> _it) {
        team = new CustList<PseudoPokemonPlayer>();
        for (PokemonPlayer p: _team) {
            team.add(new PseudoPokemonPlayer(p));
        }
        items = _it;
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

    public CustList<CustList<StringList>> getUsedStones() {
        return usedStones;
    }

    public CustList<CustList<NameLevel>> getEvolutions() {
        return evolutions;
    }
}
