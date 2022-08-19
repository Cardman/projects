package cards.consts;

import code.util.Bytes;
import code.util.core.IndexConstants;

public final class SortedPlayers {
    private final int nombreJoueurs;

    public SortedPlayers(int _nb) {
        this.nombreJoueurs = _nb;
    }
    public int getNombreJoueurs() {
        return nombreJoueurs;
    }
    public static Bytes intersectionJoueurs(Bytes _joueurs1, Bytes _joueurs2) {
        Bytes joueurs_ = new Bytes();
        for (byte j : _joueurs1) {
            if(!_joueurs2.containsObj(j)) {
                continue;
            }
            joueurs_.add(j);
        }
        return joueurs_;
    }

    public static Bytes autresJoueurs(Bytes _joueurs,
                               byte _nombreJoueurs) {
        Bytes joueurs_ = new Bytes();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (!_joueurs.containsObj(joueur_)) {
                joueurs_.add(joueur_);
            }
        }
        return joueurs_;
    }
    public byte getNextPlayer(int _player) {
        int next_ = _player;
        next_++;
        return (byte) (next_%getNombreJoueurs());
    }

    public Bytes getSortedPlayers(int _player) {
        Bytes players_ = new Bytes();
        int next_ = _player;
        next_ = (byte) (next_%getNombreJoueurs());
        while (players_.size() < getNombreJoueurs()) {
            players_.add((byte) next_);
            next_ = getNextPlayer(next_);
        }
        return players_;
    }

    public Bytes getSortedPlayersAfter(int _player) {
        Bytes players_ = new Bytes();
        int next_ = _player;
        next_++;
        next_ = (byte) (next_%getNombreJoueurs());
        while (players_.size() < getNombreJoueurs()) {
            players_.add((byte) next_);
            next_ = getNextPlayer(next_);
        }
        return players_;
    }
}
