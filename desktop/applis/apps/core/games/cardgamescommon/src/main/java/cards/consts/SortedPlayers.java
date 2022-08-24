package cards.consts;

import code.util.Bytes;
import code.util.core.IndexConstants;

public final class SortedPlayers {
    private final int nombreJoueurs;

    public SortedPlayers(int _nb) {
        this.nombreJoueurs = _nb;
    }

    public static void nextPlayers(Bytes _joueursRepartitionConnueMemo, Bytes _joueursRepartitionInconnue, byte _nbPlayers) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            if (!_joueursRepartitionConnueMemo.containsObj(joueur_)) {
                _joueursRepartitionInconnue.add(joueur_);
            }
        }
    }

    public static void shift(Bytes _joueursRepartitionConnue, Bytes _joueursRepartitionConnue2, Bytes _joueursRepartitionInconnue) {
        _joueursRepartitionInconnue.clear();
        _joueursRepartitionConnue.clear();
        _joueursRepartitionConnue.addAllElts(_joueursRepartitionConnue2);
        _joueursRepartitionConnue2.clear();
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
    public int index(int _joueur, int _entameur, int _total) {
        if(_total<nombreJoueurs) {
            //Pli en_ cours_
            if(_joueur>=_entameur) {
                return _joueur-_entameur;
            }
            return _joueur+nombreJoueurs-_entameur;
        }
        //Pli non_ separe_
        if(_joueur>=_entameur) {
            return _joueur-_entameur;
        }
        return _joueur-_entameur+nombreJoueurs;
    }

    public Bytes joueursAyantJoue(int _starter, int _total) {
        Bytes joueurs_=new Bytes();
        for(byte j : getSortedPlayers(_starter)) {
            if (aJoue(j, _total, _starter)) {
                joueurs_.add(j);
            }
        }
        return joueurs_;
    }
    public Bytes joueursAyantJoueAvant(int _pnumero, int _starter, int _total){
        Bytes joueurs_=new Bytes();
        for(byte j : getSortedPlayers(_starter)) {
            if (aJoue(j, _total, _starter)) {
                if (j == _pnumero) {
                    break;
                }
                joueurs_.add(j);
            }
        }
        return joueurs_;
    }
    public boolean aJoue(int _joueur, int _total, int _entameur) {
        if(_total<nombreJoueurs) {
            //Pli en_ cours_
            if(_joueur>=_entameur) {
                return _joueur - _entameur < _total;
            }
            return _joueur - _entameur + nombreJoueurs < _total;
        }
        //Pli non_ separe_
        return true;
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
