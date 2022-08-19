package cards.consts;

import code.util.Bytes;

public final class SortedPlayers {
    private final int nombreJoueurs;

    public SortedPlayers(int _nb) {
        this.nombreJoueurs = _nb;
    }
    public int getNombreJoueurs() {
        return nombreJoueurs;
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
